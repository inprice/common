package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Site;

public class SiteMapper implements RowMapper<Site> {

  @Override
  public Site map(ResultSet rs, StatementContext ctx) throws SQLException {
    Site m = new Site();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "active")) m.setActive(rs.getBoolean("active"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "domain")) m.setDomain(rs.getString("domain"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));
    if (Helper.hasColumn(rs, "class_name")) m.setClassName(rs.getString("class_name"));
    if (Helper.hasColumn(rs, "status")) m.setStatus(rs.getString("status"));
    if (Helper.hasColumn(rs, "logo_url")) m.setLogoUrl(rs.getString("logo_url"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}