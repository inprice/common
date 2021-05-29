package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.TicketReply;

public class TicketReplyMapper implements RowMapper<TicketReply> {

  @Override
  public TicketReply map(ResultSet rs, StatementContext ctx) throws SQLException {
    TicketReply m = new TicketReply();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "ticket_id")) m.setTicketId(rs.getLong("ticket_id"));
    if (Helper.hasColumn(rs, "editable")) m.setEditable(rs.getBoolean("editable"));
    if (Helper.hasColumn(rs, "reply")) m.setReply(rs.getString("reply"));
    if (Helper.hasColumn(rs, "from_user")) m.setFromUser(rs.getBoolean("from_user"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));
    
    return m;
  }

}