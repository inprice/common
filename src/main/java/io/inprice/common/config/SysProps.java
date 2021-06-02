package io.inprice.common.config;

import io.inprice.common.meta.AppEnv;
import io.inprice.common.utils.NumberUtils;

public class SysProps {

	public static final AppEnv APP_ENV;
  public static final boolean APP_SHOW_QUERIES;
  public static final int HTTP_CONNECTION_TIMEOUT;
  public static final int WAITING_TIME_FOR_TERMINATION;
	public static final int TPOOL_LINK_CONSUMER_CAPACITY;

  public static final String REDIS_HOST;
  public static final int REDIS_PORT;
  public static final String REDIS_PASSWORD;
  public static final String REDIS_ACTIVE_LINKS_TOPIC;
  public static final String REDIS_STATUS_CHANGE_TOPIC;
	
	public static final String DB_DRIVER;
  public static final String DB_HOST;
  public static final int DB_PORT;
  public static final String DB_DATABASE;
  public static final String DB_USERNAME;
  public static final String DB_PASSWORD;
  public static final String DB_ADDITIONS;
	
	static {
  	APP_ENV = AppEnv.valueOf(System.getenv().get("APP_ENV") != null ? System.getenv().get("APP_ENV") : "DEV");
  	APP_SHOW_QUERIES = "true".equals(System.getenv().getOrDefault("APP_SHOW_QUERIES", "false").toLowerCase());
  	HTTP_CONNECTION_TIMEOUT = NumberUtils.toInteger(System.getenv().getOrDefault("HTTP_CONNECTION_TIMEOUT", "10")); //seconds
  	WAITING_TIME_FOR_TERMINATION = NumberUtils.toInteger(System.getenv().getOrDefault("WAITING_TIME_FOR_TERMINATION", "30"));
  	TPOOL_LINK_CONSUMER_CAPACITY =  NumberUtils.toInteger(System.getenv().getOrDefault("TPOOL_LINK_CONSUMER_CAPACITY", "3"));

  	REDIS_HOST = System.getenv().getOrDefault("REDIS_HOST", "127.0.0.1");
  	REDIS_PORT = NumberUtils.toInteger(System.getenv().getOrDefault("REDIS_PORT", "6379"));
  	REDIS_PASSWORD = System.getenv().getOrDefault("REDIS_PASSWORD", null);
  	REDIS_ACTIVE_LINKS_TOPIC = System.getenv().getOrDefault("REDIS_ACTIVE_LINKS_TOPIC", "active-links");
  	REDIS_STATUS_CHANGE_TOPIC = System.getenv().getOrDefault("REDIS_STATUS_CHANGE_TOPIC", "status-change");

  	DB_DRIVER = System.getenv().getOrDefault("DB_DRIVER", APP_ENV.equals(AppEnv.TEST) ? "h2" : "mysql");
  	DB_HOST = System.getenv().getOrDefault("DB_HOST", APP_ENV.equals(AppEnv.TEST) ? "mem" : "//127.0.0.1");
  	DB_PORT = NumberUtils.toInteger(System.getenv().getOrDefault("DB_PORT", "3306"));
  	DB_DATABASE = System.getenv().getOrDefault("DB_DATABASE", APP_ENV.equals(AppEnv.TEST) ? "test" : "inprice");
  	DB_USERNAME = System.getenv().getOrDefault("DB_USERNAME", APP_ENV.equals(AppEnv.TEST) ? "sa" : "root");
  	DB_PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", APP_ENV.equals(AppEnv.TEST) ? "" : "1234");
  	DB_ADDITIONS = System.getenv().getOrDefault("DB_ADDITIONS", 
			APP_ENV.equals(AppEnv.TEST)
      ? ";init=runscript from 'classpath:db/schema.sql'; runscript from 'classpath:db/data.sql'"
      : "?useSSL=false"
		);
	}

}
