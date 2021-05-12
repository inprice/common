package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.PlanFeature;

public class PlanFeatureMapper implements RowMapper<PlanFeature> {

  @Override
  public PlanFeature map(ResultSet rs, StatementContext ctx) throws SQLException {
    PlanFeature m = new PlanFeature();

    if (Helper.hasColumn(rs, "planId")) m.setPlanId(rs.getInt("planId"));
    if (Helper.hasColumn(rs, "feature")) m.setFeature(rs.getString("feature"));
    if (Helper.hasColumn(rs, "allowed")) m.setAllowed(rs.getBoolean("allowed"));

    return m;
  }

}