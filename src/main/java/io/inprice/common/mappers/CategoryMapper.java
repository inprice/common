package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Category;

public class CategoryMapper implements RowMapper<Category> {

  @Override
  public Category map(ResultSet rs, StatementContext ctx) throws SQLException {
    Category m = new Category();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));

    return m;
  }

}