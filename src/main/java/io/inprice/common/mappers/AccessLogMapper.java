package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.AccessLog;

public class AccessLogMapper implements RowMapper<AccessLog> {

  @Override
  public AccessLog map(ResultSet rs, StatementContext ctx) throws SQLException {
    AccessLog m = new AccessLog();

		if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    
    if (Helper.hasColumn(rs, "user_email")) m.setUserEmail(rs.getString("user_email"));
    if (Helper.hasColumn(rs, "user_role")) m.setUserRole(rs.getString("user_role"));
    if (Helper.hasColumn(rs, "workspace_name")) m.setWorkspaceName(rs.getString("workspace_name"));
    if (Helper.hasColumn(rs, "method")) m.setMethod(rs.getString("method"));
    if (Helper.hasColumn(rs, "path")) m.setPath(rs.getString("path"));
    if (Helper.hasColumn(rs, "path_ext")) m.setPathExt(rs.getString("path_ext"));
    if (Helper.hasColumn(rs, "req_body")) m.setReqBody(rs.getString("req_body"));
    if (Helper.hasColumn(rs, "res_body")) m.setResBody(rs.getString("res_body"));
    if (Helper.hasColumn(rs, "status")) m.setStatus(rs.getInt("status"));
    if (Helper.hasColumn(rs, "elapsed")) m.setElapsed(rs.getInt("elapsed"));
    if (Helper.hasColumn(rs, "slow")) m.setSlow(rs.getBoolean("slow"));
    if (Helper.hasColumn(rs, "ip")) m.setIp(rs.getString("ip"));
    if (Helper.hasColumn(rs, "agent")) m.setAgent(rs.getString("agent"));

    if (Helper.hasColumn(rs, "user_id")) m.setUserId(Helper.nullLongHandler(rs, "user_id"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(Helper.nullLongHandler(rs, "workspace_id"));

    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    if (Helper.hasColumn(rs, "created_year")) m.setCreatedYear(rs.getInt("created_year"));
    if (Helper.hasColumn(rs, "created_month")) m.setCreatedMonth(rs.getString("created_month"));
    
    return m;
  }

}