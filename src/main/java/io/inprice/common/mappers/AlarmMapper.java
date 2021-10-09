package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Alarm;

public class AlarmMapper implements RowMapper<Alarm> {

  @Override
  public Alarm map(ResultSet rs, StatementContext ctx) throws SQLException {
  	Alarm m = Helper.mapForAlarm(rs);

  	if (Helper.hasColumn(rs, "_name")) {
  		String val = rs.getString("_name");
  		if (val != null) m.setName(val);
  	}

  	if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
  	if (Helper.hasColumn(rs, "full_name")) m.setFullName(rs.getString("full_name"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
  	
  	return m;
  }

}