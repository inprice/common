package io.inprice.common.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jdbi.v3.core.statement.OutParameters;

import io.inprice.common.info.ProductRefreshResult;
import io.inprice.common.meta.Position;

public class ProductRefreshResultConverter {

	public static ProductRefreshResult convert(OutParameters result) {
		ProductRefreshResult m = new ProductRefreshResult();

		if (result.getObject("productPrice") != null) m.setProductPrice(new BigDecimal(result.getDouble("productPrice")).setScale(2, RoundingMode.HALF_UP));
		if (result.getObject("basePrice") != null) m.setBasePrice(new BigDecimal(result.getDouble("basePrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("minPrice") != null) m.setMinPrice(new BigDecimal(result.getDouble("minPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("avgPrice") != null) m.setAvgPrice(new BigDecimal(result.getDouble("avgPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("maxPrice") != null) m.setMaxPrice(new BigDecimal(result.getDouble("maxPrice")).setScale(2, RoundingMode.HALF_UP));

  	if (result.getObject("alarmId") != null && result.getLong("alarmId") > 0) m.setAlarmId(result.getLong("alarmId"));
  	if (result.getObject("smartPriceId") != null && result.getLong("smartPriceId") > 0) m.setSmartPriceId(result.getLong("smartPriceId"));

  	if (result.getObject("actives") != null && result.getInt("actives") > 0) m.setActives(result.getInt("actives"));

  	if (result.getObject("position") != null) { 
    	String val = result.getString("position");
    	if (val != null) m.setPosition(Position.valueOf(val));
  	}

		return m;
	}
	
}
