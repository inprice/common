package io.inprice.common.mappers.analytics;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.mappers.Helper;
import io.inprice.common.models.analytics.AccessLog;

public class AccessLogMapper implements RowMapper<AccessLog> {

  @Override
  public AccessLog map(ResultSet rs, StatementContext ctx) throws SQLException {
    AccessLog m = new AccessLog();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));
    if (Helper.hasColumn(rs, "user_email")) m.setUserEmail(rs.getString("user_email"));
    if (Helper.hasColumn(rs, "user_role")) m.setUserRole(rs.getString("user_role"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));
    if (Helper.hasColumn(rs, "account_name")) m.setAccountName(rs.getString("account_name"));
    if (Helper.hasColumn(rs, "method")) m.setMethod(rs.getString("method"));
    if (Helper.hasColumn(rs, "path")) m.setPath(rs.getString("path"));
    if (Helper.hasColumn(rs, "path_ext")) m.setPathExt(rs.getString("path_ext"));
    if (Helper.hasColumn(rs, "req_body")) m.setReqBody(rs.getString("req_body"));
    if (Helper.hasColumn(rs, "res_body")) m.setResBody(rs.getString("res_body"));
    if (Helper.hasColumn(rs, "status")) m.setStatus(rs.getInt("status"));
    if (Helper.hasColumn(rs, "elapsed")) m.setElapsed(rs.getInt("elapsed"));
    if (Helper.hasColumn(rs, "is_slow")) m.setIsSlow(rs.getBoolean("is_slow"));
    if (Helper.hasColumn(rs, "ip")) m.setIp(rs.getString("ip"));
    if (Helper.hasColumn(rs, "agent")) m.setAgent(rs.getString("agent"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    
    return m;
  }

}