package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Voucher;

public class VoucherMapper implements RowMapper<Voucher> {

  @Override
  public Voucher map(ResultSet rs, StatementContext ctx) throws SQLException {
    Voucher m = new Voucher();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "code")) m.setCode(rs.getString("code"));
    if (Helper.hasColumn(rs, "plan_id")) m.setPlanId(rs.getInt("plan_id"));
    if (Helper.hasColumn(rs, "days")) m.setDays(rs.getInt("days"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "issuer_id")) m.setIssuerId(Helper.nullLongHandler(rs, "issuer_id"));
    if (Helper.hasColumn(rs, "issued_id")) m.setIssuedId(Helper.nullLongHandler(rs, "issued_id"));
    if (Helper.hasColumn(rs, "issued_at")) m.setIssuedAt(rs.getTimestamp("issued_at"));

    return m;
  }

}