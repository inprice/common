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
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "title")) m.setTitle(rs.getString("title"));
    if (Helper.hasColumn(rs, "content")) m.setContent(rs.getString("content"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(rs.getLong("user_id"));
    if (Helper.hasColumn(rs, "lasted_at")) m.setLastedAt(rs.getTimestamp("lasted_at"));

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