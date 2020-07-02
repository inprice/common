package io.inprice.common.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.config.SysProps;
import io.inprice.common.meta.AppEnv;

public class Database {

  private static final Logger log = LoggerFactory.getLogger(Database.class);

  private HikariDataSource ds;
  private final String conString;

  public Database() {
    this.conString = String.format("jdbc:%s:%s:%d/%s%s", SysProps.DB_DRIVER(), SysProps.DB_HOST(), SysProps.DB_PORT(),
        SysProps.DB_DATABASE(), SysProps.DB_ADDITIONS());
    log.info("Connection string: " + conString);
    connect();
  }

  public void reset() {
    if (SysProps.APP_ENV().equals(AppEnv.TEST)) {
      shutdown();
      connect();
    }
  }

  private void doMigrations() {
    try {
      Flyway flyway = Flyway.configure().dataSource(this.conString, SysProps.DB_USERNAME(), SysProps.DB_PASSWORD())
          .load();
      flyway.migrate();
    } catch (Exception e) {
      log.warn("connection string: "+ this.conString);
      log.warn("username: "+ SysProps.DB_USERNAME());
      log.warn("password: "+ SysProps.DB_PASSWORD());
      log.error("Unable to init migrations", e);
    }
  }

  private void connect() {
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

      ds = new HikariDataSource(hConf);
    } catch (Exception e) {
      log.error("Unable to connect to db", e);
    }
  }

  public Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  public Connection getTransactionalConnection() throws SQLException {
    Connection con = ds.getConnection();
    con.setAutoCommit(false);
    return con;
  }

  public void commit(Connection con) {
    try {
      con.commit();
      con.setAutoCommit(true);
    } catch (SQLException ex) {
      //
    }
  }

  public void rollback(Connection con) {
    try {
      con.rollback();
      con.setAutoCommit(true);
    } catch (SQLException ex) {
      //
    }
  }

  public void close(Connection con) {
    try {
      con.close();
    } catch (SQLException ex) {
      //
    }
  }

  private void close(Connection con, Statement pst) {
    try {
      if (pst != null)
        pst.close();
      con.close();
    } catch (SQLException ex) {
      //
    }
  }

  public <M> M findSingle(String query, ModelMapper<M> mapper) {
    try (Connection con = getConnection()) {
      return findSingle(con, query, mapper);
    } catch (Exception e) {
      log.error("Failed to find model", e);
    }

    return null;
  }

  public <M> M findSingle(Connection con, String query, ModelMapper<M> mapper) {
    String newQuery = query.trim().toLowerCase();
    if (newQuery.indexOf("limit ") < 0) {
      if (newQuery.indexOf(";") < 0) {
        newQuery += " limit 1";
      } else {
        newQuery = newQuery.replace(";", " limit 1;");
      }
    }

    try (PreparedStatement pst = con.prepareStatement(newQuery); ResultSet rs = pst.executeQuery()) {

      if (rs.next()) {
        return mapper.map(rs);
      }
    } catch (Exception e) {
      log.error("Failed to find model. " + newQuery, e);
    }

    return null;
  }

  public <M> List<M> findMultiple(String query, ModelMapper<M> mapper) {
    List<M> result = new ArrayList<>();
    try (Connection con = getConnection()) {
      result = findMultiple(con, query, mapper);
    } catch (Exception e) {
      log.error("Failed to fetch models", e);
    }

    return result;
  }

  public <M> List<M> findMultiple(Connection con, String query, ModelMapper<M> mapper) {
    List<M> result = new ArrayList<>();
    try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {
        result.add(mapper.map(rs));
      }
    } catch (Exception e) {
      log.error("Failed to fetch models", e);
    }

    return result;
  }

  /**
   * For single executions with a continual transaction
   *
   */
  public boolean executeQuery(Connection con, String query, String errorMessage) {
    if (con == null) {
      return executeQuery(query, errorMessage);
    }

    try (PreparedStatement pst = con.prepareStatement(query)) {
      int affected = pst.executeUpdate();
      return affected > 0;
    } catch (Exception e) {
      log.error(errorMessage, e);
    }
    return false;
  }

  /**
   * For single executions without any continual transaction
   *
   */
  public boolean executeQuery(String query, String errorMessage) {
    try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
      if (SysProps.APP_SHOW_QUERIES()) {
        log.info(" Q-> " + query);
      }
      return pst.executeUpdate() > 0;
    } catch (Exception e) {
      log.error(errorMessage, e);
    }
    return false;
  }

  public boolean executeBatchQueries(Connection con, List<String> queries, String errorMessage) {
    if (con == null) {
      return executeBatchQueries(queries, errorMessage);
    }

    boolean result = false;

    try (Statement sta = con.createStatement()) {
      for (String query : queries) {
          if (SysProps.APP_SHOW_QUERIES()) {
            log.info(" Q-> " + query);
          }
          sta.addBatch(query);
      }

      int[] affected = sta.executeBatch();

      result = true;
      for (int aff : affected) {
        if (aff < 1)
          return false;
      }
    } catch (SQLException e) {
      log.error(errorMessage, e);
    }
    return result;
  }

  public void executeBatchQueries(Connection con, List<String> queries) throws SQLException {
    try (Statement sta = con.createStatement()) {
      for (String query : queries) {
          if (SysProps.APP_SHOW_QUERIES()) {
            log.info(" Q-> " + query);
          }
          sta.addBatch(query);
      }
      sta.executeBatch();
    }
  }

  /**
   * For batch executions without any continual transaction
   * It is expected to be successful all the queries given as parameter
   *
   */
  public boolean executeBatchQueries(List<String> queries, String errorMessage) {
    return executeBatchQueries(queries, errorMessage, queries.size());
  }

  public boolean executeBatchQueries(List<String> queries, String errorMessage, int expectedSuccessfulStatementCount) {
    boolean result = false;

    Connection con = null;
    Statement sta = null;
    try {
      con = getTransactionalConnection();
      sta = con.createStatement();

      for (String query : queries) {
        sta.addBatch(query);
        if (SysProps.APP_SHOW_QUERIES()) {
          log.info(" Q-> " + query);
        }
      }

      int[] affected = sta.executeBatch();

      result = false;
      int successfulStatementCount = 0;

      for (int aff : affected) {
        if (expectedSuccessfulStatementCount > 0) {
          if (aff > 0)
            successfulStatementCount++;
          if (successfulStatementCount >= expectedSuccessfulStatementCount) {
            result = true;
            break;
          }
        } else {
          if (aff < 1) {
            break;
          }
        }
      }

      if (result) {
        commit(con);
      } else {
        rollback(con);
        if (!SysProps.APP_ENV().equals(AppEnv.TEST) && !errorMessage.contains("to delete"))
          log.error(errorMessage);
      }

    } catch (SQLException e) {
      if (con != null)
        rollback(con);
      log.error(errorMessage, e);
    } finally {
      if (con != null)
        close(con, sta);
    }
    return result;
  }

  public void shutdown() {
    ds.close();
  }

}
