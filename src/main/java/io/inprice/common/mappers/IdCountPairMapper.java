package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.Pair;

public class IdCountPairMapper implements RowMapper<Pair<Long, Integer>> {

  @Override
  public Pair<Long, Integer> map(ResultSet rs, StatementContext ctx) throws SQLException {
    Pair<Long, Integer> m = new Pair<>();

    if (Helper.hasColumn(rs, "_id")) m.setLeft(rs.getLong("_id"));
    if (Helper.hasColumn(rs, "_count")) m.setRight(rs.getInt("_count"));

    return m;
  }

}