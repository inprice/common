package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.UserBanned;

public class UserBannedMapper implements RowMapper<UserBanned> {

  @Override
  public UserBanned map(ResultSet rs, StatementContext ctx) throws SQLException {
    UserBanned m = new UserBanned();

    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "reason")) m.setReason(rs.getString("reason"));
    if (Helper.hasColumn(rs, "banned_at")) m.setBannedAt(rs.getTimestamp("banned_at"));

    return m;
  }

}