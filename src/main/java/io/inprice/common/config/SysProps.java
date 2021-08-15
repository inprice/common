package io.inprice.common.config;

import io.inprice.common.meta.AppEnv;
import io.inprice.common.utils.NumberUtils;

public class SysProps {

	public static final AppEnv APP_ENV;
  public static final boolean APP_SHOW_QUERIES;
  public static final int HTTP_CONNECTION_TIMEOUT;
  public static final int WAITING_TIME_FOR_TERMINATION;

  public static final String RABBIT_HOST;
  public static final int RABBIT_PORT;
  public static final String RABBIT_USERNAME;
  public static final String RABBIT_PASSWORD;

  public static final String REDIS_HOST;
  public static final int REDIS_PORT;
  public static final String REDIS_PASSWORD;

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

  	RABBIT_HOST = System.getenv().getOrDefault("RABBIT_HOST", "127.0.0.1");
  	RABBIT_PORT = NumberUtils.toInteger(System.getenv().getOrDefault("RABBIT_PORT", "5672"));
  	RABBIT_USERNAME = System.getenv().getOrDefault("RABBIT_USERNAME", "guest");
  	RABBIT_PASSWORD = System.getenv().getOrDefault("RABBIT_PASSWORD", "guest");

  	REDIS_HOST = System.getenv().getOrDefault("REDIS_HOST", "127.0.0.1");
  	REDIS_PORT = NumberUtils.toInteger(System.getenv().getOrDefault("REDIS_PORT", "6379"));
  	REDIS_PASSWORD = System.getenv().getOrDefault("REDIS_PASSWORD", null);

  	DB_DRIVER = System.getenv().getOrDefault("DB_DRIVER", "mysql");
  	DB_HOST = System.getenv().getOrDefault("DB_HOST", "//127.0.0.1");
  	DB_PORT = NumberUtils.toInteger(System.getenv().getOrDefault("DB_PORT", "3306"));
  	DB_DATABASE = System.getenv().getOrDefault("DB_DATABASE", APP_ENV.equals(AppEnv.TEST) ? "test" : "inprice");
  	DB_USERNAME = System.getenv().getOrDefault("DB_USERNAME", "root");
  	DB_PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "1234");
  	DB_ADDITIONS = System.getenv().getOrDefault("DB_ADDITIONS", "?useSSL=false");
	}

}
