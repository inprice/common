package io.inprice.common.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.mappers.Helper;
import io.inprice.common.meta.Level;
import io.inprice.common.meta.LinkStatus;

public class LinkSummaryMapper implements RowMapper<LinkSummary> {

  @Override
  public LinkSummary map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkSummary m = new LinkSummary();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "platform")) m.setPlatform(rs.getString("platform"));
    if (Helper.hasColumn(rs, "seller")) m.setSeller(rs.getString("seller"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "ranking")) m.setRanking(rs.getInt("ranking"));
    if (Helper.hasColumn(rs, "group_price")) m.setGroupPrice(rs.getBigDecimal("group_price"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));

    if (Helper.hasColumn(rs, "level")) {
      String val = rs.getString("level");
      if (val != null) m.setLevel(Level.valueOf(val));
    }

    if (Helper.hasColumn(rs, "status")) {
      String val = rs.getString("status");
      if (val != null) m.setStatus(LinkStatus.valueOf(val));
    }
    
    return m;
  }

}