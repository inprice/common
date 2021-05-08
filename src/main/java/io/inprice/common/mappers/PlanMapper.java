package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Plan;

public class PlanMapper implements RowMapper<Plan> {

  @Override
  public Plan map(ResultSet rs, StatementContext ctx) throws SQLException {
    Plan m = new Plan();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getInt("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "features")) m.setFeatures(rs.getString("features"));
    if (Helper.hasColumn(rs, "link_limit")) m.setLinkLimit(rs.getInt("link_limit"));
    if (Helper.hasColumn(rs, "alarm_limit")) m.setAlarmLimit(rs.getInt("alarm_limit"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}