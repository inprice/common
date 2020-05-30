package io.inprice.scrapper.common.config;

import io.inprice.scrapper.common.meta.AppEnv;

public class SysProps {

  public static AppEnv APP_ENV() {
    AppEnv val = AppEnv.DEV;
    try {
      val = AppEnv.valueOf(System.getenv().get("APP_ENV").toUpperCase());
    } catch (Exception ignored) { }
    return val;
  }

  public static int WAITING_TIME_FOR_TERMINATION() {
    return new Integer(System.getenv().getOrDefault("WAITING_TIME_FOR_TERMINATION", "30"));
  }

  public static boolean APP_SHOW_QUERIES() {
    return "true".equals(System.getenv().getOrDefault("APP_SHOW_QUERIES", "false").toLowerCase());
  }

  public static String MQ_HOST() {
    return System.getenv().getOrDefault("MQ_HOST", "localhost");
  }

  public static int MQ_PORT() {
    return new Integer(System.getenv().getOrDefault("MQ_PORT", "5672"));
  }

  public static String MQ_USERNAME() {
    return System.getenv().getOrDefault("MQ_USERNAME", "guest");
  }

  public static String MQ_PASSWORD() {
    return System.getenv().getOrDefault("MQ_PASSWORD", "guest");
  }

  public static String MQ_LINKS_EXCHANGE() {
    return System.getenv().getOrDefault("MQ_LINKS_EXCHANGE", "links");
  }

  public static String MQ_CHANGES_EXCHANGE() {
    return System.getenv().getOrDefault("MQ_CHANGES_EXCHANGE", "changes");
  }

  public static String MQ_DEAD_LETTERS_EXCHANGE() {
    return System.getenv().getOrDefault("MQ_DEAD_LETTERS_EXCHANGE", "dead-letters");
  }

  public static String MQ_DELETED_LINKS_ROUTING() {
    return System.getenv().getOrDefault("MQ_DELETED_LINKS_ROUTING", "links.deleted");
  }

  public static String MQ_NEW_LINKS_ROUTING() {
    return System.getenv().getOrDefault("MQ_NEW_LINKS_ROUTING", "links.new");
  }

  public static String MQ_FAILED_LINKS_ROUTING() {
    return System.getenv().getOrDefault("MQ_FAILED_LINKS_ROUTING", "links.failed");
  }

  public static String MQ_AVAILABLE_LINKS_ROUTING() {
    return System.getenv().getOrDefault("MQ_AVAILABLE_LINKS_ROUTING", "links.available");
  }

  public static String MQ_TOBE_AVAILABLE_LINKS_ROUTING() {
    return System.getenv().getOrDefault("MQ_TOBE_AVAILABLE_LINKS_ROUTING", "links.tobe-available");
  }

  public static String MQ_STATUS_CHANGES_ROUTING() {
    return System.getenv().getOrDefault("MQ_STATUS_CHANGES_ROUTING", "changes.status");
  }

  public static String MQ_PRICE_CHANGES_ROUTING() {
    return System.getenv().getOrDefault("MQ_PRICE_CHANGES_ROUTING", "changes.price");
  }

  public static String MQ_NEW_LINKS_QUEUE() {
    return System.getenv().getOrDefault("MQ_NEW_LINKS_QUEUE", "new-links");
  }

  public static String MQ_AVALIABLE_LINKS_QUEUE() {
    return System.getenv().getOrDefault("MQ_AVALIABLE_LINKS_QUEUE", "available-links");
  }

  public static String MQ_FAILED_LINKS_QUEUE() {
    return System.getenv().getOrDefault("MQ_FAILED_LINKS_QUEUE", "failed-links");
  }

  public static String MQ_TOBE_AVAILABLE_LINKS_QUEUE() {
    return System.getenv().getOrDefault("MQ_QUEUE_TOBE_AVAILABLE_LINK", "tobe-available-links");
  }

  public static String MQ_DELETED_LINKS_QUEUE() {
    return System.getenv().getOrDefault("MQ_DELETED_LINKS_QUEUE", "deleted-links");
  }

  public static String MQ_STATUS_CHANGE_QUEUE() {
    return System.getenv().getOrDefault("MQ_STATUS_CHANGE_QUEUE", "status-changes");
  }

  public static String MQ_PRICE_CHANGE_QUEUE() {
    return System.getenv().getOrDefault("MQ_PRICE_CHANGE_QUEUE", "price-changes");
  }

  public static String MQ_STATUS_DEAD_LETTERS_QUEUE() {
    return System.getenv().getOrDefault("MQ_DEAD_LETTERS_QUEUE", "dead-letters");
  }

  public static String DB_DRIVER() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "h2" : "mysql";
    return System.getenv().getOrDefault("DB_DRIVER", def);
  }

  public static String DB_HOST() {
    String def = APP_ENV().equals(AppEnv.TEST) ? "mem" : "//localhost";
    return System.getenv().getOrDefault("DB_HOST", def);
  }

  public static int DB_PORT() {
    return new Integer(System.getenv().getOrDefault("DB_PORT", "3306"));
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
        : "";
    return System.getenv().getOrDefault("DB_ADDITIONS", def);
  }

  public static String REDIS_HOST() {
    return System.getenv().getOrDefault("REDIS_HOST", "localhost");
  }

  public static int REDIS_PORT() {
    return new Integer(System.getenv().getOrDefault("REDIS_PORT", "6379"));
  }

  public static String REDIS_PASSWORD() {
    return System.getenv().getOrDefault("REDIS_PASSWORD", null);
  }

}
