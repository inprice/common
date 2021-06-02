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
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "_hash")) m.setHash(rs.getString("_hash"));
    if (Helper.hasColumn(rs, "session_id")) m.setSessionId(rs.getString("session_id"));
    if (Helper.hasColumn(rs, "plan_id")) m.setPlanId(rs.getInt("plan_id"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(CheckoutStatus.valueOf(status));
    }

    return m;
  }

}