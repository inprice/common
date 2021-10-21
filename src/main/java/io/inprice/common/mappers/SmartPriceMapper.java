package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.SmartPrice;

public class SmartPriceMapper implements RowMapper<SmartPrice> {

  @Override
  public SmartPrice map(ResultSet rs, StatementContext ctx) throws SQLException {
    SmartPrice m = new SmartPrice();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "formula")) m.setFormula(rs.getString("formula"));
    if (Helper.hasColumn(rs, "lower_limit_formula")) m.setLowerLimitFormula(rs.getString("lower_limit_formula"));
    if (Helper.hasColumn(rs, "upper_limit_formula")) m.setUpperLimitFormula(rs.getString("upper_limit_formula"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));

    return m;
  }

}