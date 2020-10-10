package io.inprice.common.helpers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.config.SysProps;
import io.inprice.common.meta.AppEnv;

public class Database {

  private static final Logger log = LoggerFactory.getLogger(Database.class);

  private static HikariDataSource dataSource;
  private static Jdbi jdbi;
  private static final String conString;

  static {
    conString = 
      String.format("jdbc:%s:%s:%d/%s%s", 
        SysProps.DB_DRIVER(), 
        SysProps.DB_HOST(), 
        SysProps.DB_PORT(),
        SysProps.DB_DATABASE(), 
        SysProps.DB_ADDITIONS()
      );
    log.info("Connection string: " + conString);
    connect();
  }

  public static Handle getHandle() {
    if (jdbi == null) connect();
    return jdbi.open();
  }

  public static void shutdown() {
    dataSource.close();
  }

  public static void reset() {
    if (SysProps.APP_ENV().equals(AppEnv.TEST)) {
      shutdown();
      connect();
    }
  }

  private static void doMigrations() {
    try {
      Flyway flyway = 
        Flyway
          .configure()
          .dataSource(conString, SysProps.DB_USERNAME(), SysProps.DB_PASSWORD())
          .load();
      flyway.migrate();
    } catch (Exception e) {
      log.warn("Connection: "+ conString);
      if (! SysProps.APP_ENV().equals(AppEnv.PROD)) {
        log.warn("username: "+ SysProps.DB_USERNAME());
        log.warn("password: "+ SysProps.DB_PASSWORD());
      }
      log.error("Unable to init migrations", e);
    }
  }

  private static void connect() {
    doMigrations();

    try {
      HikariConfig hConf = new HikariConfig();
      hConf.setJdbcUrl(conString);
      hConf.setUsername(SysProps.DB_USERNAME());
      hConf.setPassword(SysProps.DB_PASSWORD());

      if (SysProps.APP_ENV().equals(AppEnv.TEST))
        hConf.setConnectionTimeout(3 * 1000); // three seconds
      else
        hConf.setConnectionTimeout(10 * 1000); // ten seconds

      hConf.addDataSourceProperty("cachePrepStmts", "true");
      hConf.addDataSourceProperty("prepStmtCacheSize", "250");
      hConf.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      hConf.addDataSourceProperty("useServerPrepStmts", "true");
      hConf.addDataSourceProperty("useLocalSessionState", "true");
      hConf.addDataSourceProperty("rewriteBatchedStatements", "true");
      hConf.addDataSourceProperty("cacheResultSetMetadata", "true");
      hConf.addDataSourceProperty("cacheServerConfiguration", "true");
      //hConf.addDataSourceProperty("elideSetAutoCommits", "true");
      hConf.addDataSourceProperty("maintainTimeStats", "false");

      dataSource = new HikariDataSource(hConf);
      jdbi = Jdbi.create(dataSource);
      jdbi.installPlugin(new SqlObjectPlugin());
    } catch (Exception e) {
      log.error("Unable to connect to db", e);
    }
  }

}
