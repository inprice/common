package io.inprice.common.repository;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.ProductMapper;
import io.inprice.common.models.Product;

public interface ProductPriceDao {

  final String ALARM_FIELDS = 
      ", al.subject, al.subject_when, al.certain_position, al.amount_lower_limit " +
      ", al.amount_upper_limit, al.last_position, al.last_amount, al.tobe_notified, al.notified_at, al.updated_at as as_updated_at ";

  final String SMART_PRICE_FIELDS = ", sp.formula, sp.lower_limit_formula, sp.upper_limit_formula ";

	@SqlQuery(
  	"select p.*" + ALARM_FIELDS + " from product p " + 
    "inner join alarm as al on al.id = p.alarm_id " + 
		"where p.id=:productId "
	)
	@UseRowMapper(ProductMapper.class)
	Product findProductAndAlarmById(@Bind("productId") Long productId);

	@SqlQuery(
  	"select p.*" + SMART_PRICE_FIELDS + " from product p " + 
    "inner join smart_price as sp on sp.id = p.smart_price_id " + 
		"where p.id =:productId "
	)
	@UseRowMapper(ProductMapper.class)
	Product findProductAndSmartPriceById(@Bind("productId") Long productId);
  
}
