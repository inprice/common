package io.inprice.common.helpers;

import io.inprice.common.config.RedisConf;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class Redis {

  private static JedisPool pool;

  /**
   * Must be called during the application starting up
   */
  public static void start(RedisConf conf) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    pool = new JedisPool(
  		poolConfig, 
  		conf.HOST,
  		conf.PORT, 
  		Protocol.DEFAULT_TIMEOUT, 
  		conf.PASSWORD
		);
  }

  public static JedisPool getPool() {
		return pool;
	}

  /**
   * Must be called during the application shutting down
   */
  public static void stop() {
  	try {
  		pool.close();
  	} catch (Exception ignore) {}
  }

}
