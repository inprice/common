package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.TicketPriority;
import io.inprice.common.meta.TicketStatus;
import io.inprice.common.meta.TicketSubject;
import io.inprice.common.meta.TicketType;
import io.inprice.common.models.TicketHistory;

public class TicketHistoryMapper implements RowMapper<TicketHistory> {

  @Override
  public TicketHistory map(ResultSet rs, StatementContext ctx) throws SQLException {
    TicketHistory m = new TicketHistory();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "ticket_id")) m.setTicketId(rs.getLong("ticket_id"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));
    
    if (Helper.hasColumn(rs, "status")) {
    	String val = rs.getString("status");
    	if (val != null) m.setStatus(TicketStatus.valueOf(val));
    }

    if (Helper.hasColumn(rs, "priority")) {
    	String val = rs.getString("priority");
    	if (val != null) m.setPriority(TicketPriority.valueOf(val));
    }
    
    if (Helper.hasColumn(rs, "type")) {
    	String val = rs.getString("type");
    	if (val != null) m.setType(TicketType.valueOf(val));
    }

    if (Helper.hasColumn(rs, "subject")) {
      String val = rs.getString("subject");
      if (val != null) m.setSubject(TicketSubject.valueOf(val));
    }

    //transients
    if (Helper.hasColumn(rs, "full_name")) m.setFullName(rs.getString("full_name"));
   
    return m;
  }

}