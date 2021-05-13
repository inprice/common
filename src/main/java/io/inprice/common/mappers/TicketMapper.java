package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.TicketCSatLevel;
import io.inprice.common.meta.TicketSubject;
import io.inprice.common.meta.TicketType;
import io.inprice.common.models.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

  @Override
  public Ticket map(ResultSet rs, StatementContext ctx) throws SQLException {
    Ticket m = new Ticket();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getInt("id"));
    if (Helper.hasColumn(rs, "query")) m.setQuery(rs.getString("query"));
    if (Helper.hasColumn(rs, "reply")) m.setReply(rs.getString("reply"));
    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "group_id")) m.setGroupId(rs.getLong("group_id"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));
    if (Helper.hasColumn(rs, "csated_at")) m.setCsatedAt(rs.getTimestamp("csated_at"));
    if (Helper.hasColumn(rs, "replied_at")) m.setRepliedAt(rs.getTimestamp("replied_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    
    if (Helper.hasColumn(rs, "type")) {
    	String val = rs.getString("type");
    	if (val != null) m.setType(TicketType.valueOf(val));
    }

    if (Helper.hasColumn(rs, "subject")) {
      String val = rs.getString("subject");
      if (val != null) m.setSubject(TicketSubject.valueOf(val));
    }
    
    if (Helper.hasColumn(rs, "csat_level")) {
    	String val = rs.getString("csat_level");
    	if (val != null) m.setCsatLevel(TicketCSatLevel.valueOf(val));
    }
    if (Helper.hasColumn(rs, "csat_assessment")) m.setCsatAssessment(rs.getString("csat_assessment"));

    return m;
  }

}