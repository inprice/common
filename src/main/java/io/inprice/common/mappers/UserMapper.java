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

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "timezone")) m.setTimezone(rs.getString("timezone"));
    if (Helper.hasColumn(rs, "password_hash")) m.setPasswordHash(rs.getString("password_hash"));
    if (Helper.hasColumn(rs, "password_salt")) m.setPasswordSalt(rs.getString("password_salt"));
    if (Helper.hasColumn(rs, "stripe_cust_id")) m.setStripeCustId(rs.getString("stripe_cust_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}