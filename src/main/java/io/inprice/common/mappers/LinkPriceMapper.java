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

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "position")) m.setPosition(Helper.nullIntegerHandler(rs, "position"));
    if (Helper.hasColumn(rs, "diff_amount")) m.setDiffAmount(rs.getBigDecimal("diff_amount"));
    if (Helper.hasColumn(rs, "diff_rate")) m.setDiffRate(rs.getBigDecimal("diff_rate"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}