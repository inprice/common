package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.Link;

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
    if (Helper.hasColumn(rs, "last_check")) m.setLastCheck(rs.getTimestamp("last_check"));
    if (Helper.hasColumn(rs, "last_update")) m.setLastUpdate(rs.getTimestamp("last_update"));
    if (Helper.hasColumn(rs, "retry")) m.setRetry(Helper.nullIntegerHandler(rs, "retry"));
    if (Helper.hasColumn(rs, "http_status")) m.setHttpStatus(Helper.nullIntegerHandler(rs, "http_status"));
    if (Helper.hasColumn(rs, "website_class_name")) m.setWebsiteClassName(rs.getString("website_class_name"));
    if (Helper.hasColumn(rs, "site_id")) m.setSiteId(Helper.nullLongHandler(rs, "site_id"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(Helper.nullLongHandler(rs, "product_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(LinkStatus.valueOf(status));
    }
    if (Helper.hasColumn(rs, "pre_status")) {
      String preStatus = rs.getString("pre_status");
      if (preStatus != null) m.setPreStatus(LinkStatus.valueOf(preStatus));
    }

    //transients
    if (Helper.hasColumn(rs, "platform")) m.setPlatform(rs.getString("platform"));
    if (Helper.hasColumn(rs, "product_price")) m.setProductPrice(rs.getBigDecimal("product_price"));

    return m;
  }

}