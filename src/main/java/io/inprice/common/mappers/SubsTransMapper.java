package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.SubsEvent;
import io.inprice.common.models.SubsTrans;

public class SubsTransMapper implements RowMapper<SubsTrans> {

  @Override
  public SubsTrans map(ResultSet rs, StatementContext ctx) throws SQLException {
    SubsTrans m = new SubsTrans();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "event_id")) m.setEventId(rs.getString("event_id"));
    if (Helper.hasColumn(rs, "event")) m.setEvent(SubsEvent.findByDescription(rs.getString("event")));
    if (Helper.hasColumn(rs, "successful")) m.setSuccessful(rs.getBoolean("successful"));
    if (Helper.hasColumn(rs, "reason")) m.setReason(rs.getString("reason"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "file_url")) m.setFileUrl(rs.getString("file_url"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}