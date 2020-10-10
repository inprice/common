package io.inprice.common.mappers;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Product;
import io.inprice.common.models.ProductPrice;

public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
    Product m = new Product();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "active")) m.setActive(rs.getBoolean("active"));
    if (Helper.hasColumn(rs, "code")) m.setCode(rs.getString("code"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "position")) m.setPosition(rs.getInt("position"));
    if (Helper.hasColumn(rs, "last_price_id")) m.setLastPriceId(Helper.nullLongHandler(rs, "last_price_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(Helper.nullLongHandler(rs, "company_id"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    ProductPrice pp = new ProductPrice();
    if (m.getLastPriceId() != null) {
      if (Helper.hasColumn(rs, "price")) pp.setPrice(rs.getBigDecimal("price"));
      if (Helper.hasColumn(rs, "min_platform")) pp.setMinPlatform(rs.getString("min_platform"));
      if (Helper.hasColumn(rs, "min_seller")) pp.setMinSeller(rs.getString("min_seller"));
      if (Helper.hasColumn(rs, "min_price")) pp.setMinPrice(rs.getBigDecimal("min_price"));
      if (Helper.hasColumn(rs, "min_diff")) pp.setMinDiff(rs.getBigDecimal("min_diff"));
      if (Helper.hasColumn(rs, "avg_price")) pp.setAvgPrice(rs.getBigDecimal("avg_price"));
      if (Helper.hasColumn(rs, "avg_diff")) pp.setAvgDiff(rs.getBigDecimal("avg_diff"));
      if (Helper.hasColumn(rs, "max_platform")) pp.setMaxPlatform(rs.getString("max_platform"));
      if (Helper.hasColumn(rs, "max_seller")) pp.setMaxSeller(rs.getString("max_seller"));
      if (Helper.hasColumn(rs, "max_price")) pp.setMaxPrice(rs.getBigDecimal("max_price"));
      if (Helper.hasColumn(rs, "max_diff")) pp.setMaxDiff(rs.getBigDecimal("max_diff"));
      if (Helper.hasColumn(rs, "links")) pp.setLinks(rs.getInt("links"));
      if (Helper.hasColumn(rs, "position")) pp.setPosition(rs.getInt("position"));
      if (Helper.hasColumn(rs, "ranking")) pp.setRanking(rs.getInt("ranking"));
      if (Helper.hasColumn(rs, "ranking_with")) pp.setRankingWith(rs.getInt("ranking_with"));
      if (Helper.hasColumn(rs, "suggested_price")) pp.setSuggestedPrice(rs.getBigDecimal("suggested_price"));
      if (Helper.hasColumn(rs, "created_at")) pp.setCreatedAt(rs.getTimestamp("created_at"));
    }
    m.setPriceDetails(pp);

    return m;
  }

}