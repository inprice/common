package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.LinkPrice;

public class LinkPriceMapper implements RowMapper<LinkPrice> {

  @Override
  public LinkPrice map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkPrice m = new LinkPrice();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "old_price")) m.setOldPrice(rs.getBigDecimal("old_price"));
    if (Helper.hasColumn(rs, "new_price")) m.setNewPrice(rs.getBigDecimal("new_price"));
    if (Helper.hasColumn(rs, "diff_amount")) m.setDiffAmount(rs.getBigDecimal("diff_amount"));
    if (Helper.hasColumn(rs, "diff_rate")) m.setDiffRate(rs.getBigDecimal("diff_rate"));
    if (Helper.hasColumn(rs, "group_id")) m.setGroupId(rs.getLong("group_id"));

    return m;
  }

}