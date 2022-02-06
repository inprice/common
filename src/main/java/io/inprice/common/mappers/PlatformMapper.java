package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Platform;

public class PlatformMapper implements RowMapper<Platform> {

  @Override
  public Platform map(ResultSet rs, StatementContext ctx) throws SQLException {
    Platform m = new Platform();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "domain")) m.setDomain(rs.getString("domain"));
    if (Helper.hasColumn(rs, "class_name")) m.setClassName(rs.getString("class_name"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));
    if (Helper.hasColumn(rs, "currency_code")) m.setCurrencyCode(rs.getString("currency_code"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "parked")) m.setParked(rs.getBoolean("parked"));
    if (Helper.hasColumn(rs, "blocked")) m.setBlocked(rs.getBoolean("blocked"));
    if (Helper.hasColumn(rs, "queue")) m.setQueue(rs.getString("queue"));
    if (Helper.hasColumn(rs, "profile")) m.setProfile(rs.getString("profile"));

    return m;
  }

}