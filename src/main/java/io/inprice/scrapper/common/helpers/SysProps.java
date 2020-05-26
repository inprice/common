package io.inprice.scrapper.common.helpers;

public class SysProps {

  public static boolean IS_RUN_FOR_DEV() {
    return !"prod".equals(System.getenv().getOrDefault("APP_ENV", "prod").toLowerCase());
  }

  public static String DB_DRIVER() {
    String def = IS_RUN_FOR_DEV() ? "h2" : "mysql";
    return System.getenv().getOrDefault("DB_DRIVER", def);
  }

  public static String DB_HOST() {
    String def = IS_RUN_FOR_DEV() ? "mem" : "//localhost";
    return System.getenv().getOrDefault("DB_HOST", def);
  }

  public static int DB_PORT() {
    return new Integer(System.getenv().getOrDefault("DB_PORT", "3306"));
  }

  public static String DB_DATABASE() {
    String def = IS_RUN_FOR_DEV() ? "test" : "inprice";
    return System.getenv().getOrDefault("DB_DATABASE", def);
  }

  public static String DB_USERNAME() {
    String def = IS_RUN_FOR_DEV() ? "sa" : "root";
    return System.getenv().getOrDefault("DB_USERNAME", def);
  }

  public static String DB_PASSWORD() {
    String def = IS_RUN_FOR_DEV() ? "" : "1234";
    return System.getenv().getOrDefault("DB_PASSWORD", def);
  }

  public static String DB_ADDITIONS() {
    String def = IS_RUN_FOR_DEV()
        ? ";init=runscript from 'classpath:db/schema.sql'; runscript from 'classpath:db/data.sql'"
        : "";
    return System.getenv().getOrDefault("DB_ADDITIONS", def);
  }

  public static boolean APP_SHOW_QUERIES() {
    return "true".equals(System.getenv().getOrDefault("APP_SHOW_QUERIES", "false").toLowerCase());
  }

}
