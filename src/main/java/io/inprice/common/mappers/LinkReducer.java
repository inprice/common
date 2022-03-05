package io.inprice.common.mappers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import io.inprice.common.helpers.GlobalConsts;
import io.inprice.common.meta.Grup;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.Position;
import io.inprice.common.models.Link;
import io.inprice.common.models.Platform;
import io.inprice.common.utils.StringHelper;

public class LinkReducer implements LinkedHashMapRowReducer<Long, Link> {

  @Override
  public void accumulate(Map<Long, Link> map, RowView rw) {
    Link m = map.get(Helper.getColumnVal(rw, "id", Long.class));

    if (m == null) {
	  	m = new Link();
	
	  	m.setId(Helper.getColumnVal(rw, "id", Long.class));
	    m.setUrl(Helper.getColumnVal(rw, "url", String.class));
	    m.setUrlHash(Helper.getColumnVal(rw, "url_hash", String.class));
	    m.setSku(Helper.getColumnVal(rw, "sku", String.class));
	    m.setName(Helper.getColumnVal(rw, "name", String.class));
	    m.setBrand(Helper.getColumnVal(rw, "brand", String.class));
	    m.setSeller(Helper.getColumnVal(rw, "seller", String.class));
	    m.setShipment(Helper.getColumnVal(rw, "shipment", String.class));
	    m.setPrice(Helper.getColumnVal(rw, "price", BigDecimal.class));
	    m.setPriceDirection(Helper.getColumnVal(rw, "price_direction", Integer.class, 0));
	    m.setRetry(Helper.getColumnVal(rw, "retry", Integer.class, 1));
	    m.setWatchlisted(Helper.getColumnVal(rw, "watchlisted", Boolean.class, Boolean.FALSE));
	    m.setParseCode(Helper.getColumnVal(rw, "parse_code", String.class));
	    m.setParseProblem(Helper.getColumnVal(rw, "parse_problem", String.class));
	    m.setAlarmId(Helper.getColumnVal(rw, "alarm_id", Long.class));
	    m.setAlarmedAt(Helper.getColumnVal(rw, "alarmed_at", Timestamp.class));
	    m.setTobeAlarmed(Helper.getColumnVal(rw, "tobe_alarmed", Boolean.class, Boolean.FALSE));
	    m.setPlatformId(Helper.getColumnVal(rw, "platform_id", Long.class));
	    m.setProductId(Helper.getColumnVal(rw, "product_id", Long.class));
	    m.setCheckedAt(Helper.getColumnVal(rw, "checked_at", Timestamp.class));
	    m.setUpdatedAt(Helper.getColumnVal(rw, "updated_at", Timestamp.class));
	
    	String val = Helper.getColumnVal(rw, "position", String.class);
    	if (val != null) m.setPosition(Position.valueOf(val));

    	val = Helper.getColumnVal(rw, "pre_status", String.class);
    	if (val != null) m.setPreStatus(LinkStatus.valueOf(val));

    	val = Helper.getColumnVal(rw, "status", String.class);
    	if (val != null) m.setStatus(LinkStatus.valueOf(val));

    	val = Helper.getColumnVal(rw, "grup", String.class);
    	if (val != null) m.setGrup(Grup.valueOf(val));
	
	    m.setWorkspaceId(Helper.getColumnVal(rw, "workspace_id", Long.class));
	    m.setCreatedAt(Helper.getColumnVal(rw, "created_at", Timestamp.class));
	    m.setCreatedYear(Helper.getColumnVal(rw, "created_year", Integer.class));
	    m.setCreatedMonth(Helper.getColumnVal(rw, "created_month", String.class));
	    m.setImporterWsId(Helper.getColumnVal(rw, "importer_ws_id", Long.class));

	    //transients
	    m.setProductName(Helper.getColumnVal(rw, "product_name", String.class));
	    m.setProductPrice(Helper.getColumnVal(rw, "product_price", BigDecimal.class, BigDecimal.ZERO));
	    m.setProductBasePrice(Helper.getColumnVal(rw, "product_base_price", BigDecimal.class, BigDecimal.ZERO));
	
    	val = Helper.getColumnVal(rw, "product_position", String.class);
    	if (val != null) m.setProductPosition(Position.valueOf(val));
	
	    m.setProductAlarmId(Helper.getColumnVal(rw, "product_alarm_id", Long.class));
	    m.setProductSmartPriceId(Helper.getColumnVal(rw, "product_smart_price_id", Long.class));
	
	  	if (m.getPlatformId() != null) {
	    	Platform platform = new Platform();
	    	platform.setId(m.getPlatformId());
	    	platform.setDomain(Helper.getColumnVal(rw, "domain", String.class));
	    	platform.setClassName(Helper.getColumnVal(rw, "class_name", String.class));
	    	platform.setCountry(Helper.getColumnVal(rw, "country", String.class));
	    	platform.setCurrencyCode(Helper.getColumnVal(rw, "currency_code", String.class));
	    	platform.setCurrencyFormat(Helper.getColumnVal(rw, "currency_format", String.class));
	    	platform.setParked(Helper.getColumnVal(rw, "parked", Boolean.class, Boolean.FALSE));
	    	platform.setBlocked(Helper.getColumnVal(rw, "blocked", Boolean.class, Boolean.FALSE));
	    	platform.setQueue(Helper.getColumnVal(rw, "queue", String.class));
	    	m.setPlatform(platform);
	  	}
	
	    m.setAlarmName(Helper.getColumnVal(rw, "al_name", String.class));
	
	    //seller and url must be masked for demo account!
	    if (m.getWorkspaceId() == GlobalConsts.DEMO_WS_ID && Helper.getColumnVal(rw, "is_masked", Boolean.class, Boolean.FALSE) == Boolean.TRUE) {
				String maskedSeller = (StringUtils.isNotBlank(m.getSeller()) && GlobalConsts.NOT_AVAILABLE.equals(m.getSeller()) == false ? StringHelper.maskString(m.getSeller()) : null);
				if (maskedSeller != null) {
					m.setUrl(m.getUrl().replaceAll(m.getSeller(), maskedSeller));
					m.setSeller(maskedSeller);
				}
				m.setUrl(m.getUrl().substring(0, m.getUrl().length()-12) + "-masked-url");
	    }
    }

  	BigDecimal lpPrice = Helper.getColumnVal(rw, "lp_price", BigDecimal.class, BigDecimal.ZERO);
    m.getPrices().add(lpPrice);
    //duplication for the first price is needed because sparkline component at fe side doesn't render properly without this!!!
    if (m.getPrices().size() == 1) m.getPrices().add(lpPrice);
    if (m.getPrices().size() > 25) m.getPrices().remove(0);

    map.put(m.getId(), m);
  }

}