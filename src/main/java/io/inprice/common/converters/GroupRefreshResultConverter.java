package io.inprice.common.converters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jdbi.v3.core.statement.OutParameters;

import io.inprice.common.info.GroupRefreshResult;
import io.inprice.common.meta.Level;

public class GroupRefreshResultConverter {

	public static GroupRefreshResult convert(OutParameters result) {
		GroupRefreshResult m = new GroupRefreshResult();

  	if (result.getObject("minPrice") != null) m.setMinPrice(new BigDecimal(result.getDouble("minPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("avgPrice") != null) m.setAvgPrice(new BigDecimal(result.getDouble("avgPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("maxPrice") != null) m.setMaxPrice(new BigDecimal(result.getDouble("maxPrice")).setScale(2, RoundingMode.HALF_UP));
  	if (result.getObject("total") != null) m.setTotal(new BigDecimal(result.getDouble("total")).setScale(2, RoundingMode.HALF_UP));
  	
  	if (result.getObject("level") != null) { 
    	String val = result.getString("level");
    	if (val != null) m.setLevel(Level.valueOf(val));
  	}

		return m;
	}
	
}
