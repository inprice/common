package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.Pair;

public class IdNamePairMapper implements RowMapper<Pair<Long, String>> {

  @Override
  public Pair<Long, String> map(ResultSet rs, StatementContext ctx) throws SQLException {
    Pair<Long, String> m = new Pair<Long, String>();

    if (Helper.hasColumn(rs, "id")) m.setLeft(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setRight(rs.getString("name"));

    return m;
  }

}