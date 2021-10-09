package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.User;

public class UserMapper implements RowMapper<User> {

  @Override
  public User map(ResultSet rs, StatementContext ctx) throws SQLException {
    User m = new User();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "password")) m.setPassword(rs.getString("password"));
    if (Helper.hasColumn(rs, "full_name")) m.setFullName(rs.getString("full_name"));
    if (Helper.hasColumn(rs, "timezone")) m.setTimezone(rs.getString("timezone"));
    if (Helper.hasColumn(rs, "privileged")) m.setPrivileged(rs.getBoolean("privileged"));
    if (Helper.hasColumn(rs, "banned")) m.setBanned(rs.getBoolean("banned"));
    if (Helper.hasColumn(rs, "banned_at")) m.setBannedAt(rs.getTimestamp("banned_at"));
    if (Helper.hasColumn(rs, "ban_reason")) m.setBanReason(rs.getString("ban_reason"));

    return m;
  }

}