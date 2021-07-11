package io.inprice.common.helpers;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.config.SysProps;
import io.inprice.common.meta.AppEnv;

public class BaseRedisClient {

  private static final Logger log = LoggerFactory.getLogger(BaseRedisClient.class);

  private RedissonClient client;
  private volatile boolean isHealthy;
  private volatile boolean isCancelled;

  public void open(Runnable callback) {
    String redisPass = SysProps.REDIS_PASSWORD;
    
    Config config = new Config();
    config
      .useSingleServer()
      .setAddress(String.format("redis://%s:%d", SysProps.REDIS_HOST, SysProps.REDIS_PORT))
      .setPassword(StringUtils.isNotBlank(redisPass) ? redisPass : null)
      .setConnectionPoolSize(SysProps.APP_ENV.equals(AppEnv.PROD) ? 10 : 1)
      .setConnectionMinimumIdleSize(1)
      .setIdleConnectionTimeout(SysProps.APP_ENV.equals(AppEnv.PROD) ? 5000 : 1000)
      .setTimeout(SysProps.APP_ENV.equals(AppEnv.PROD) ? 5000 : 1000);

    while (!isHealthy && !isCancelled) {
      try {
        client = Redisson.create(config);
        if (callback != null) callback.run();
        isHealthy = true;
      } catch (Exception e) {
        log.error("Failed to connect to Redis server, trying again in 3 seconds!", e.getMessage());
        try {
          Thread.sleep(3000);
        } catch (InterruptedException ignored) { }
      }
    }
        
  }

  public RedissonClient getClient() {
    return client;
  }

  public boolean isHealthy() {
    return this.isHealthy;
  }

  public void shutdown() {
    this.isCancelled = true;
    if (client != null) {
      client.shutdown();
    } else {
      log.warn("No redis client found!");
    }
  }

}
