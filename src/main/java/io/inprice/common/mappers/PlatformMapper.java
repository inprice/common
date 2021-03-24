package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.Platform;

public class PlatformMapper implements RowMapper<Platform> {

  @Override
  public Platform map(ResultSet rs, StatementContext ctx) throws SQLException {
    Platform m = new Platform();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "domain")) m.setDomain(rs.getString("domain"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));
    if (Helper.hasColumn(rs, "class_name")) m.setClassName(rs.getString("class_name"));
    if (Helper.hasColumn(rs, "problem")) m.setProblem(rs.getString("problem"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(LinkStatus.valueOf(status));
    }

    return m;
  }

}