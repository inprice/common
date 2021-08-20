package io.inprice.common.helpers;

import java.time.temporal.ChronoUnit;

import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.inprice.common.config.MysqlConf;

public class Database {

  private static final Logger logger = LoggerFactory.getLogger(Database.class);

  private static HikariDataSource dataSource;
  private static Jdbi jdbi;

  private static MysqlConf dbConf;
  private static String conString;

  public static void start(MysqlConf conf) {
  	Database.dbConf = conf;
    conString = 
      String.format("jdbc:%s://%s:%d/%s%s", 
    		conf.DRIVER, 
    		conf.HOST, 
    		conf.PORT,
    		conf.DATABASE, 
    		conf.ADDITIONS
      );
    logger.info("Connection string: " + conString);
    connect();
  }

  public static Handle getHandle() {
    if (jdbi == null) connect();
    return jdbi.open();
  }

  public static void stop() {
    dataSource.close();
  }

  private static void doMigrations() {
    try {
      Flyway flyway = 
        Flyway
          .configure()
          .dataSource(conString, dbConf.USERNAME, dbConf.PASSWORD)
          .load();
      flyway.migrate();
    } catch (Exception e) {
      logger.error("Connection: " + conString, e);
    }
  }

  public static void cleanDBForTests(String scripts, String env) {
  	if (env.equals("test") == false) {
  		logger.warn("You are not allowed to clean tables since system profile is {}!", env);
  		return;
  	}
  	jdbi.useTransaction(handle -> {
  		handle.createScript(scripts).execute();
      logger.info("Test tables are cleaned!");
  	});
  }

  private static void connect() {
    doMigrations();

    try {
      HikariConfig hConf = new HikariConfig();
      hConf.setJdbcUrl(conString);
      hConf.setUsername(dbConf.USERNAME);
      hConf.setPassword(dbConf.PASSWORD);
      hConf.setConnectionTimeout(10 * 1000); // ten seconds

      hConf.addDataSourceProperty("cachePrepStmts", "true");
      hConf.addDataSourceProperty("prepStmtCacheSize", "250");
      hConf.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      hConf.addDataSourceProperty("useServerPrepStmts", "true");
      hConf.addDataSourceProperty("useLocalSessionState", "true");
      hConf.addDataSourceProperty("rewriteBatchedStatements", "true");
      hConf.addDataSourceProperty("cacheResultSetMetadata", "true");
      hConf.addDataSourceProperty("cacheServerConfiguration", "true");
      hConf.addDataSourceProperty("maintainTimeStats", "false");

      SqlLogger sqlLogger = new SqlLogger() {
        @Override
        public void logAfterExecution(StatementContext context) {
          if (dbConf.SHOW_QUERIES) {
            logger.info(" -- Time: {}ms, Query: {}, Parameters: {}", 
              context.getElapsedTime(ChronoUnit.MILLIS), context.getRenderedSql(), context.getBinding().toString());
          }
        }
      };

      dataSource = new HikariDataSource(hConf);
      jdbi = 
        Jdbi.create(dataSource)
          .setSqlLogger(sqlLogger)
          .installPlugin(new SqlObjectPlugin());
    } catch (Exception e) {
      logger.error("Unable to connect to db", e);
    }
  }

}
