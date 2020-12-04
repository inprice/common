package io.inprice.common.config;

import io.inprice.common.meta.AppEnv;
import io.inprice.common.utils.NumberUtils;

public class SysProps {

  public static AppEnv APP_ENV() {
    AppEnv val = AppEnv.DEV;
    try {
      val = AppEnv.valueOf(System.getenv().get("APP_ENV").toUpperCase());
    } catch (Exception ignored) { }
    return val;
  }

  public static int WAITING_TIME_FOR_TERMINATION() {
    return NumberUtils.toInteger(System.getenv().getOrDefault("WAITING_TIME_FOR_TERMINATION", "30"));
  }

  public static boolean APP_SHOW_QUERIES() {
    return "true".equals(System.getenv().getOrDefault("APP_SHOW_QUERIES", "false").toLowerCase());
  }

  public static int HTTP_CONNECTION_TIMEOUT() {
    return NumberUtils.toInteger(System.getenv().getOrDefault("HTTP_CONNECTION_TIMEOUT", "10"));
  }

  public static String DB_DRIVER() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "h2" : "mysql";
    return System.getenv().getOrDefault("DB_DRIVER", def);
  }

  public static String DB_HOST() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "mem" : "//127.0.0.1";
    return System.getenv().getOrDefault("DB_HOST", def);
  }

  public static int DB_PORT() {
    return NumberUtils.toInteger(System.getenv().getOrDefault("DB_PORT", "3306"));
  }

  public static String DB_DATABASE() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "test" : "inprice";
    return System.getenv().getOrDefault("DB_DATABASE", def);
  }

  public static String DB_USERNAME() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "sa" : "root";
    return System.getenv().getOrDefault("DB_USERNAME", def);
  }

  public static String DB_PASSWORD() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "" : "1234";
    return System.getenv().getOrDefault("DB_PASSWORD", def);
  }

  public static String DB_ADDITIONS() {
    String def = APP_ENV().equals(AppEnv.TEST)
        ? ";init=runscript from 'classpath:db/schema.sql'; runscript from 'classpath:db/data.sql'"
        : "?useSSL=false";
    return System.getenv().getOrDefault("DB_ADDITIONS", def);
  }

  public static String REDIS_HOST() {
    return System.getenv().getOrDefault("REDIS_HOST", "127.0.0.1");
  }

  public static int REDIS_PORT() {
    return NumberUtils.toInteger(System.getenv().getOrDefault("REDIS_PORT", "6379"));
  }

  public static String REDIS_PASSWORD() {
    return System.getenv().getOrDefault("REDIS_PASSWORD", null);
  }

  public static String REDIS_ACTIVE_LINKS_TOPIC() {
    return System.getenv().getOrDefault("REDIS_ACTIVE_LINKS_TOPIC", "active-links");
  }

  public static String REDIS_STATUS_CHANGE_TOPIC() {
    return System.getenv().getOrDefault("REDIS_STATUS_CHANGE_TOPIC", "status-change");
  }

}
