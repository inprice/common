package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Position;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.Grup;
import io.inprice.common.models.Link;
import io.inprice.common.models.Platform;

public class LinkMapper implements RowMapper<Link> {

  @Override
  public Link map(ResultSet rs, StatementContext ctx) throws SQLException {
    Link m = new Link();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "url")) m.setUrl(rs.getString("url"));
    if (Helper.hasColumn(rs, "url_hash")) m.setUrlHash(rs.getString("url_hash"));
    if (Helper.hasColumn(rs, "sku")) m.setSku(rs.getString("sku"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "brand")) m.setBrand(rs.getString("brand"));
    if (Helper.hasColumn(rs, "seller")) m.setSeller(rs.getString("seller"));
    if (Helper.hasColumn(rs, "shipment")) m.setShipment(rs.getString("shipment"));
    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "price_direction")) m.setPriceDirection(Helper.nullIntegerHandler(rs, "price_direction"));
    if (Helper.hasColumn(rs, "retry")) m.setRetry(Helper.nullIntegerHandler(rs, "retry"));
    if (Helper.hasColumn(rs, "watchlisted")) m.setWatchlisted(rs.getBoolean("watchlisted"));

    if (Helper.hasColumn(rs, "parse_code")) m.setParseCode(rs.getString("parse_code"));
    if (Helper.hasColumn(rs, "parse_problem")) m.setParseProblem(rs.getString("parse_problem"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));

    if (Helper.hasColumn(rs, "checked_at")) m.setCheckedAt(rs.getTimestamp("checked_at"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));

    if (Helper.hasColumn(rs, "platform_id")) m.setPlatformId(Helper.nullLongHandler(rs, "platform_id"));
    if (Helper.hasColumn(rs, "alarm_id")) m.setAlarmId(Helper.nullLongHandler(rs, "alarm_id"));

    if (Helper.hasColumn(rs, "position")) {
    	String val = rs.getString("position");
    	if (val != null) m.setPosition(Position.valueOf(val));
    }
    if (Helper.hasColumn(rs, "pre_status")) {
    	String val = rs.getString("pre_status");
    	if (val != null) m.setPreStatus(LinkStatus.valueOf(val));
    }
    if (Helper.hasColumn(rs, "status")) {
    	String val = rs.getString("status");
    	if (val != null) m.setStatus(LinkStatus.valueOf(val));
    }
    if (Helper.hasColumn(rs, "grup")) {
    	String val = rs.getString("grup");
    	if (val != null) m.setGrup(Grup.valueOf(val));
    }

    //transients
    if (Helper.hasColumn(rs, "product_name")) m.setProductName(rs.getString("product_name"));
    if (Helper.hasColumn(rs, "product_price")) m.setProductPrice(rs.getBigDecimal("product_price"));
    if (Helper.hasColumn(rs, "product_base_price")) m.setProductBasePrice(rs.getBigDecimal("product_base_price"));
    if (Helper.hasColumn(rs, "product_alarm_id")) m.setProductAlarmId(Helper.nullLongHandler(rs, "product_alarm_id"));
    if (Helper.hasColumn(rs, "product_smart_price_id")) m.setProductSmartPriceId(Helper.nullLongHandler(rs, "product_smart_price_id"));

    
  	if (m.getPlatformId() != null) {
    	Platform platform = new Platform();
    	platform.setId(m.getPlatformId());
    	if (Helper.hasColumn(rs, "platform_name")) platform.setName(rs.getString("platform_name"));
    	if (Helper.hasColumn(rs, "domain")) platform.setDomain(rs.getString("domain"));
    	if (Helper.hasColumn(rs, "class_name")) platform.setClassName(rs.getString("class_name"));
    	if (Helper.hasColumn(rs, "currency_code")) platform.setCurrencyCode(rs.getString("currency_code"));
    	if (Helper.hasColumn(rs, "currency_format")) platform.setCurrencyFormat(rs.getString("currency_format"));
    	if (Helper.hasColumn(rs, "country")) platform.setCountry(rs.getString("country"));
    	if (Helper.hasColumn(rs, "queue")) platform.setQueue(rs.getString("queue"));
    	if (Helper.hasColumn(rs, "parked")) platform.setParked(rs.getBoolean("parked"));
    	if (Helper.hasColumn(rs, "blocked")) platform.setBlocked(rs.getBoolean("blocked"));
    	if (Helper.hasColumn(rs, "profile")) platform.setProfile(rs.getString("profile"));
    	m.setPlatform(platform);
  	}

  	if (m.getAlarmId() != null && (Helper.hasColumn(rs, "tobe_notified"))) {
    	m.setAlarm(Helper.mapForAlarm(rs, m.getAlarmId(), m.getId(), null, m.getWorkspaceId()));
    }

    return m;
  }

}