package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.CheckoutStatus;
import io.inprice.common.models.Checkout;

public class CheckoutMapper implements RowMapper<Checkout> {

  @Override
  public Checkout map(ResultSet rs, StatementContext ctx) throws SQLException {
    Checkout m = new Checkout();

    if (Helper.hasColumn(rs, "_hash")) m.setHash(rs.getString("_hash"));
    if (Helper.hasColumn(rs, "session_id")) m.setSessionId(rs.getString("session_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(CheckoutStatus.valueOf(status));
    }

    return m;
  }

}