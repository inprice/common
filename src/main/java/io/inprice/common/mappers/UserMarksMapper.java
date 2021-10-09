package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.UserMarks;

public class UserMarksMapper implements RowMapper<UserMarks> {

  @Override
  public UserMarks map(ResultSet rs, StatementContext ctx) throws SQLException {
    UserMarks m = new UserMarks();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "mark")) m.setMark(rs.getString("mark"));
    if (Helper.hasColumn(rs, "boolean_val")) m.setBooleanVal(rs.getBoolean("boolean_val"));
    if (Helper.hasColumn(rs, "string_val")) m.setStringVal(rs.getString("string_val"));
    if (Helper.hasColumn(rs, "integer_val")) m.setIntegerVal(Helper.nullIntegerHandler(rs, "integer_val"));
    if (Helper.hasColumn(rs, "date_val")) m.setDateVal(rs.getDate("date_val"));

    return m;
  }

}