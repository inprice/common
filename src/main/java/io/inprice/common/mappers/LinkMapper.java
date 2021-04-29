package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Level;
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
    if (Helper.hasColumn(rs, "retry")) m.setRetry(Helper.nullIntegerHandler(rs, "retry"));
    if (Helper.hasColumn(rs, "http_status")) m.setHttpStatus(Helper.nullIntegerHandler(rs, "http_status"));
    if (Helper.hasColumn(rs, "problem")) m.setProblem(rs.getString("problem"));
    if (Helper.hasColumn(rs, "group_id")) m.setGroupId(rs.getLong("group_id"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));

    if (Helper.hasColumn(rs, "checked_at")) m.setCheckedAt(rs.getTimestamp("checked_at"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "level")) {
    	String val = rs.getString("level");
    	if (val != null) m.setLevel(Level.valueOf(val));
    }
    if (Helper.hasColumn(rs, "pre_status")) {
    	String val = rs.getString("pre_status");
    	if (val != null) m.setPreStatus(LinkStatus.valueOf(val));
    }
    if (Helper.hasColumn(rs, "status")) {
    	String val = rs.getString("status");
    	if (val != null) m.setStatus(LinkStatus.valueOf(val));
    }

    //transients
    if (Helper.hasColumn(rs, "group_name")) m.setGroupName(rs.getString("group_name"));
    if (Helper.hasColumn(rs, "group_price")) m.setGroupPrice(rs.getBigDecimal("group_price"));

  	Platform p = new Platform();
  	if (Helper.hasColumn(rs, "platform_name")) p.setName(rs.getString("platform_name"));
  	if (Helper.hasColumn(rs, "currency_code")) p.setCurrencyCode(rs.getString("currency_code"));
  	if (Helper.hasColumn(rs, "currency_format")) p.setCurrencyFormat(rs.getString("currency_format"));
  	if (Helper.hasColumn(rs, "country")) p.setCountry(rs.getString("country"));
  	
  	m.setPlatformId(p.getId());
  	m.setPlatform(p);
    
    return m;
  }

}