package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.TicketPriority;
import io.inprice.common.meta.TicketStatus;
import io.inprice.common.meta.TicketSubject;
import io.inprice.common.meta.TicketType;
import io.inprice.common.models.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

  @Override
  public Ticket map(ResultSet rs, StatementContext ctx) throws SQLException {
    Ticket m = new Ticket();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "body")) m.setBody(rs.getString("body"));
    if (Helper.hasColumn(rs, "seen_by_user")) m.setSeenByUser(rs.getBoolean("seen_by_user"));
    if (Helper.hasColumn(rs, "seen_by_super")) m.setSeenBySuper(rs.getBoolean("seen_by_super"));
    if (Helper.hasColumn(rs, "comment_count")) m.setCommentCount(rs.getInt("comment_count"));
    if (Helper.hasColumn(rs, "progressed_at")) m.setProgressedAt(rs.getTimestamp("progressed_at"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));

    //transients
    if (Helper.hasColumn(rs, "workspace")) m.setWorkspace(rs.getString("workspace"));
    if (Helper.hasColumn(rs, "full_name")) m.setFullName(rs.getString("full_name"));
    
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
    
    return m;
  }

}