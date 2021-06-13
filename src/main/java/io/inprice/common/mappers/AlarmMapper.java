package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Alarm;

public class AlarmMapper implements RowMapper<Alarm> {

  @Override
  public Alarm map(ResultSet rs, StatementContext ctx) throws SQLException {
  	return Helper.mapForAlarm(rs);
  }

}