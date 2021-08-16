package io.inprice.common.helpers;

import io.inprice.common.config.SysProps;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class Redis {

  private static JedisPool pool;

  /**
   * Must be called during the application starting up
   */
  public static void start() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    pool = new JedisPool(poolConfig, SysProps.REDIS_HOST, SysProps.REDIS_PORT, Protocol.DEFAULT_TIMEOUT, SysProps.REDIS_PASSWORD);
  }

  public static JedisPool getPool() {
		return pool;
	}

  /**
   * Must be called during the application shutting down
   */
  public static void stop() {
  	pool.close();
  }

}
