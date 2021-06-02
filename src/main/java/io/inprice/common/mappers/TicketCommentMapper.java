package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.TicketComment;

public class TicketCommentMapper implements RowMapper<TicketComment> {

  @Override
  public TicketComment map(ResultSet rs, StatementContext ctx) throws SQLException {
    TicketComment m = new TicketComment();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "ticket_id")) m.setTicketId(rs.getLong("ticket_id"));
    if (Helper.hasColumn(rs, "content")) m.setContent(rs.getString("content"));
    if (Helper.hasColumn(rs, "editable")) m.setEditable(rs.getBoolean("editable"));
    if (Helper.hasColumn(rs, "added_by_user")) m.setAddedByUser(rs.getBoolean("added_by_user"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));

    //transients
    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    
    return m;
  }

}