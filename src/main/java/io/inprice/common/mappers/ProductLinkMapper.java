package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.info.ProductLink;

public class ProductLinkMapper implements RowMapper<ProductLink> {

  @Override
  public ProductLink map(ResultSet rs, StatementContext ctx) throws SQLException {
    ProductLink m = new ProductLink();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "platform")) m.setPlatform(rs.getString("platform"));
    if (Helper.hasColumn(rs, "seller")) m.setSeller(rs.getString("seller"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "position")) m.setPosition(Helper.nullIntegerHandler(rs, "position"));
    if (Helper.hasColumn(rs, "ranking")) m.setRanking(Helper.nullIntegerHandler(rs, "ranking"));
    if (Helper.hasColumn(rs, "product_price")) m.setProductPrice(rs.getBigDecimal("product_price"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));

    return m;
  }

}