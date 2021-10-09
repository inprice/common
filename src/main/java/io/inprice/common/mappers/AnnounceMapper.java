package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.AnnounceLevel;
import io.inprice.common.meta.AnnounceType;
import io.inprice.common.models.Announce;

public class AnnounceMapper implements RowMapper<Announce> {

  @Override
  public Announce map(ResultSet rs, StatementContext ctx) throws SQLException {
    Announce m = new Announce();

		if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    
    if (Helper.hasColumn(rs, "title")) m.setTitle(rs.getString("title"));
    if (Helper.hasColumn(rs, "body")) m.setBody(rs.getString("body"));
    if (Helper.hasColumn(rs, "link")) m.setLink(rs.getString("link"));
    if (Helper.hasColumn(rs, "starting_at")) m.setStartingAt(rs.getTimestamp("starting_at"));
    if (Helper.hasColumn(rs, "ending_at")) m.setEndingAt(rs.getTimestamp("ending_at"));

    if (Helper.hasColumn(rs, "user_id")) m.setUserId(Helper.nullLongHandler(rs, "user_id"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(Helper.nullLongHandler(rs, "workspace_id"));

    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    if (Helper.hasColumn(rs, "created_year")) m.setCreatedYear(rs.getInt("created_year"));
    if (Helper.hasColumn(rs, "created_month")) m.setCreatedMonth(rs.getString("created_month"));
    
    if (Helper.hasColumn(rs, "type")) {
    	String val = rs.getString("type");
    	if (val != null) m.setType(AnnounceType.valueOf(val));
    }

    if (Helper.hasColumn(rs, "level")) {
    	String val = rs.getString("level");
    	if (val != null) m.setLevel(AnnounceLevel.valueOf(val));
    }
    
    return m;
  }

}