package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.Link;
import io.inprice.common.models.Platform;

public class LinkMapper implements RowMapper<Link> {

  @Override
  public Link map(ResultSet rs, StatementContext ctx) throws SQLException {
    Link m = new Link();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "url")) m.setUrl(rs.getString("url"));
    if (Helper.hasColumn(rs, "url_hash")) m.setUrlHash(rs.getString("url_hash"));
    if (Helper.hasColumn(rs, "sku")) m.setSku(rs.getString("sku"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "brand")) m.setBrand(rs.getString("brand"));
    if (Helper.hasColumn(rs, "seller")) m.setSeller(rs.getString("seller"));
    if (Helper.hasColumn(rs, "shipment")) m.setShipment(rs.getString("shipment"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "position")) m.setPosition(Helper.nullIntegerHandler(rs, "position"));
    if (Helper.hasColumn(rs, "last_check")) m.setLastCheck(rs.getTimestamp("last_check"));
    if (Helper.hasColumn(rs, "last_update")) m.setLastUpdate(rs.getTimestamp("last_update"));
    if (Helper.hasColumn(rs, "retry")) m.setRetry(Helper.nullIntegerHandler(rs, "retry"));
    if (Helper.hasColumn(rs, "http_status")) m.setHttpStatus(Helper.nullIntegerHandler(rs, "http_status"));
    if (Helper.hasColumn(rs, "problem")) m.setProblem(rs.getString("problem"));
    if (Helper.hasColumn(rs, "import_detail_id")) m.setImportDetailId(Helper.nullLongHandler(rs, "import_detail_id"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "pre_status")) {
    	String status = rs.getString("pre_status");
    	if (status != null) m.setPreStatus(LinkStatus.valueOf(status));
    }
    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(LinkStatus.valueOf(status));
    }

    //transients
    if (Helper.hasColumn(rs, "product_price")) m.setProductPrice(rs.getBigDecimal("product_price"));

    if (Helper.hasColumn(rs, "platform_id") || Helper.hasColumn(rs, "class_name")) {
    	Platform p = new Platform();
    	if (Helper.hasColumn(rs, "platform_id")) p.setId(rs.getLong("platform_id"));
    	if (Helper.hasColumn(rs, "class_name")) p.setClassName(rs.getString("class_name"));

    	m.setPlatformId(p.getId());
    	m.setPlatform(p);
    }
    
    return m;
  }

}