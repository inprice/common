package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.IdName;

public class IdNameMapper implements RowMapper<IdName> {

  @Override
  public IdName map(ResultSet rs, StatementContext ctx) throws SQLException {
  	IdName m = new IdName();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));

    return m;
  }

}