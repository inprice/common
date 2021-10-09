package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Brand;

public class BrandMapper implements RowMapper<Brand> {

  @Override
  public Brand map(ResultSet rs, StatementContext ctx) throws SQLException {
    Brand m = new Brand();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));

    return m;
  }

}