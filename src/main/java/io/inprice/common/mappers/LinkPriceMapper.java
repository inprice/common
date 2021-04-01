package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Level;
import io.inprice.common.models.LinkPrice;

public class LinkPriceMapper implements RowMapper<LinkPrice> {

  @Override
  public LinkPrice map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkPrice m = new LinkPrice();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "diff_amount")) m.setDiffAmount(rs.getBigDecimal("diff_amount"));
    if (Helper.hasColumn(rs, "diff_rate")) m.setDiffRate(rs.getBigDecimal("diff_rate"));
    if (Helper.hasColumn(rs, "group_id")) m.setGroupId(rs.getLong("group_id"));

    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "level")) {
      String val = rs.getString("level");
      if (val != null) m.setLevel(Level.valueOf(val));
    }

    return m;
  }

}