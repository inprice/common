package io.inprice.common.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jdbi.v3.core.statement.OutParameters;

import io.inprice.common.info.ProductRefreshResult;
import io.inprice.common.meta.Position;

public class ProductRefreshResultConverter {

	public static ProductRefreshResult convert(OutParameters result) {
		ProductRefreshResult m = new ProductRefreshResult();

  	if (result.getObject("minPrice") != null) m.setMinPrice(new BigDecimal(result.getDouble("minPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("avgPrice") != null) m.setAvgPrice(new BigDecimal(result.getDouble("avgPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("maxPrice") != null) m.setMaxPrice(new BigDecimal(result.getDouble("maxPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("alarmId") != null) m.setAlarmId(result.getLong("alarmId"));

  	if (result.getObject("position") != null) { 
    	String val = result.getString("position");
    	if (val != null) m.setPosition(Position.valueOf(val));
  	}

		return m;
	}
	
}
