package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.Product;

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
    if (Helper.hasColumn(rs, "ranking")) m.setRanking(rs.getInt("ranking"));
    if (Helper.hasColumn(rs, "ranking_with")) m.setRankingWith(rs.getInt("ranking_with"));
    if (Helper.hasColumn(rs, "min_platform")) m.setMinPlatform(rs.getString("min_platform"));
    if (Helper.hasColumn(rs, "min_seller")) m.setMinSeller(rs.getString("min_seller"));
    if (Helper.hasColumn(rs, "min_price")) m.setMinPrice(rs.getBigDecimal("min_price"));
    if (Helper.hasColumn(rs, "min_diff")) m.setMinDiff(rs.getBigDecimal("min_diff"));
    if (Helper.hasColumn(rs, "avg_price")) m.setAvgPrice(rs.getBigDecimal("avg_price"));
    if (Helper.hasColumn(rs, "avg_diff")) m.setAvgDiff(rs.getBigDecimal("avg_diff"));
    if (Helper.hasColumn(rs, "max_platform")) m.setMaxPlatform(rs.getString("max_platform"));
    if (Helper.hasColumn(rs, "max_seller")) m.setMaxSeller(rs.getString("max_seller"));
    if (Helper.hasColumn(rs, "max_price")) m.setMaxPrice(rs.getBigDecimal("max_price"));
    if (Helper.hasColumn(rs, "max_diff")) m.setMaxDiff(rs.getBigDecimal("max_diff"));
    if (Helper.hasColumn(rs, "suggested_price")) m.setSuggestedPrice(rs.getBigDecimal("suggested_price"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(Helper.nullLongHandler(rs, "company_id"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    return m;
  }

}