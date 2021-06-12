package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.Pair;

public class NameCountPairMapper implements RowMapper<Pair<String, Integer>> {

  @Override
  public Pair<String, Integer> map(ResultSet rs, StatementContext ctx) throws SQLException {
    Pair<String, Integer> m = new Pair<>();

    if (Helper.hasColumn(rs, "name")) m.setKey(rs.getString("name"));
    if (Helper.hasColumn(rs, "_count")) m.setValue(rs.getInt("_count"));

    return m;
  }

}