package io.inprice.common.repository;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.ProductMapper;
import io.inprice.common.models.Product;

public interface AlarmDao {

  final String FIELDS = 
      ", al.subject, al.subject_when, al.certain_status, al.amount_lower_limit " +
      ", al.amount_upper_limit, al.last_status, al.last_amount, al.tobe_notified, al.notified_at, al.updated_at as as_updated_at ";

	@SqlQuery(
  	"select g.*" + FIELDS + " from product g " + 
    "inner join alarm as al on al.id = g.alarm_id " + 
		"where g.id =:productId "
	)
	@UseRowMapper(ProductMapper.class)
	Product findProductAndAlarmById(@Bind("productId") Long productId);
  
}
