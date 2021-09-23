package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Level;
import io.inprice.common.models.Product;

public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
    Product m = new Product();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "code")) m.setCode(rs.getString("code"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "total")) m.setTotal(rs.getBigDecimal("total"));

    if (Helper.hasColumn(rs, "actives")) m.setActives(rs.getInt("actives"));
    if (Helper.hasColumn(rs, "waitings")) m.setWaitings(rs.getInt("waitings"));
    if (Helper.hasColumn(rs, "tryings")) m.setTryings(rs.getInt("tryings"));
    if (Helper.hasColumn(rs, "problems")) m.setProblems(rs.getInt("problems"));

    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
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
    
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));

    if (Helper.hasColumn(rs, "alarm_id")) m.setAlarmId(Helper.nullLongHandler(rs, "alarm_id"));
  	if (Helper.hasColumn(rs, "brand_id")) m.setBrandId(Helper.nullLongHandler(rs, "brand_id"));
  	if (Helper.hasColumn(rs, "category_id")) m.setCategoryId(Helper.nullLongHandler(rs, "category_id"));

    if (Helper.hasColumn(rs, "level")) {
      String val = rs.getString("level");
      if (val != null) m.setLevel(Level.valueOf(val));
    }

    //transients
    if (m.getAlarmId() != null && (Helper.hasColumn(rs, "tobe_notified"))) {
    	m.setAlarm(Helper.mapForAlarm(rs, m.getAlarmId(), null, m.getId(), m.getWorkspaceId()));
    }

  	if (Helper.hasColumn(rs, "brand_name")) m.setBrandName(rs.getString("brand_name"));
  	if (Helper.hasColumn(rs, "category_name")) m.setCategoryName(rs.getString("category_name"));

    return m;
  }

}