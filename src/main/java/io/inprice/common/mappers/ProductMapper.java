package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Position;
import io.inprice.common.models.Product;

public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
    Product m = new Product();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "sku")) m.setSku(rs.getString("sku"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));

    if (Helper.hasColumn(rs, "actives")) m.setActives(rs.getInt("actives"));
    if (Helper.hasColumn(rs, "waitings")) m.setWaitings(rs.getInt("waitings"));
    if (Helper.hasColumn(rs, "tryings")) m.setTryings(rs.getInt("tryings"));
    if (Helper.hasColumn(rs, "problems")) m.setProblems(rs.getInt("problems"));

    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "base_price")) m.setBasePrice(rs.getBigDecimal("base_price"));

    if (Helper.hasColumn(rs, "min_diff")) m.setMinDiff(rs.getBigDecimal("min_diff"));
    if (Helper.hasColumn(rs, "avg_diff")) m.setAvgDiff(rs.getBigDecimal("avg_diff"));
    if (Helper.hasColumn(rs, "max_diff")) m.setMaxDiff(rs.getBigDecimal("max_diff"));

    if (Helper.hasColumn(rs, "min_platform")) m.setMinPlatform(rs.getString("min_platform"));
    if (Helper.hasColumn(rs, "min_seller")) m.setMinSeller(rs.getString("min_seller"));
    if (Helper.hasColumn(rs, "min_price")) m.setMinPrice(rs.getBigDecimal("min_price"));
    if (Helper.hasColumn(rs, "avg_price")) m.setAvgPrice(rs.getBigDecimal("avg_price"));
    if (Helper.hasColumn(rs, "max_platform")) m.setMaxPlatform(rs.getString("max_platform"));
    if (Helper.hasColumn(rs, "max_seller")) m.setMaxSeller(rs.getString("max_seller"));
    if (Helper.hasColumn(rs, "max_price")) m.setMaxPrice(rs.getBigDecimal("max_price"));

  	if (Helper.hasColumn(rs, "alarm_id")) m.setAlarmId(Helper.nullLongHandler(rs, "alarm_id"));
    if (Helper.hasColumn(rs, "alarmed_at")) m.setAlarmedAt(rs.getTimestamp("alarmed_at"));
    if (Helper.hasColumn(rs, "tobe_alarmed")) m.setTobeAlarmed(rs.getBoolean("tobe_alarmed"));

    if (Helper.hasColumn(rs, "smart_price_id")) m.setSmartPriceId(Helper.nullLongHandler(rs, "smart_price_id"));
    if (Helper.hasColumn(rs, "suggested_price")) m.setSuggestedPrice(rs.getBigDecimal("suggested_price"));
    if (Helper.hasColumn(rs, "suggested_price_problem")) m.setSuggestedPriceProblem(rs.getString("suggested_price_problem"));
    
  	if (Helper.hasColumn(rs, "brand_id")) m.setBrandId(Helper.nullLongHandler(rs, "brand_id"));
  	if (Helper.hasColumn(rs, "category_id")) m.setCategoryId(Helper.nullLongHandler(rs, "category_id"));

    if (Helper.hasColumn(rs, "position")) {
    	String val = rs.getString("position");
    	if (val != null) m.setPosition(Position.valueOf(val));
    }

    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    
    //transients
    if (Helper.hasColumn(rs, "al_name")) m.setAlarmName(rs.getString("al_name"));
  	if (Helper.hasColumn(rs, "sp_name")) m.setSmartPriceName(rs.getString("sp_name"));

  	if (Helper.hasColumn(rs, "brand_name")) m.setBrandName(rs.getString("brand_name"));
  	if (Helper.hasColumn(rs, "category_name")) m.setCategoryName(rs.getString("category_name"));

    return m;
  }

}