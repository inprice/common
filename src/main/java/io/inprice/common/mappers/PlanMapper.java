package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.PlanType;
import io.inprice.common.models.Plan;

public class PlanMapper implements RowMapper<Plan> {

  @Override
  public Plan map(ResultSet rs, StatementContext ctx) throws SQLException {
    Plan m = new Plan();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getInt("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));

    if (Helper.hasColumn(rs, "product_limit")) m.setProductLimit(rs.getInt("product_limit"));
    if (Helper.hasColumn(rs, "alarm_limit")) m.setAlarmLimit(rs.getInt("alarm_limit"));
    if (Helper.hasColumn(rs, "user_limit")) m.setUserLimit(rs.getInt("user_limit"));

    if (Helper.hasColumn(rs, "integrations_allowed")) m.setIntegrationsAllowed(rs.getBoolean("integrations_allowed"));
    if (Helper.hasColumn(rs, "api_allowed")) m.setApiAllowed(rs.getBoolean("api_allowed"));
    if (Helper.hasColumn(rs, "search_insert_allowed")) m.setSearcAndInsertAllowed(rs.getBoolean("search_insert_allowed"));

    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "type")) {
      String val = rs.getString("type");
      if (val != null) m.setType(PlanType.valueOf(val));
    }

    return m;
  }

}