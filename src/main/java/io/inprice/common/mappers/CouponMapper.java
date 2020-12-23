package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Coupon;

public class CouponMapper implements RowMapper<Coupon> {

  @Override
  public Coupon map(ResultSet rs, StatementContext ctx) throws SQLException {
    Coupon m = new Coupon();

    if (Helper.hasColumn(rs, "code")) m.setCode(rs.getString("code"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "days")) m.setDays(rs.getInt("days"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "issuer_id")) m.setIssuerId(Helper.nullLongHandler(rs, "issuer_id"));
    if (Helper.hasColumn(rs, "issued_id")) m.setIssuedId(Helper.nullLongHandler(rs, "issued_id"));
    if (Helper.hasColumn(rs, "issued_at")) m.setIssuedAt(rs.getTimestamp("issued_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}