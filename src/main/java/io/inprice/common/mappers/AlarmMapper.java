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
  	
  	//transients
		switch (m.getTopic()) {

			case LINK: {
      	if (Helper.hasColumn(rs, "link_name")) {
      		String val = rs.getString("link_name");
      		if (val != null) m.setName(val);
      	}
      	if (m.getName() == null && Helper.hasColumn(rs, "link_url")) {
      		String val = rs.getString("link_url");
      		if (val != null) m.setName(val);
      	}
      	break;
			}

			case GROUP: {
      	if (m.getName() == null && Helper.hasColumn(rs, "group_name")) {
      		String val = rs.getString("group_name");
      		if (val != null) m.setName(val);
      	}
      	break;
    	}
		}

  	return m;
  }

}