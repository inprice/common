package io.inprice.common.repository;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.LinkGroupMapper;
import io.inprice.common.models.LinkGroup;

public interface AlarmDao {

  final String FIELDS = 
      ", al.subject, al.subject_when, al.certain_status, al.price_lower_limit " +
      ", al.price_upper_limit, al.last_status, al.last_price, al.tobe_notified, al.notified_at, al.updated_at as as_updated_at ";

	@SqlQuery(
  	"select g.*" + FIELDS + " from link_group g " + 
    "inner join alarm as al on al.id = g.alarm_id " + 
		"where g.id =:groupId "
	)
	@UseRowMapper(LinkGroupMapper.class)
	LinkGroup findGroupAndAlarmById(@Bind("groupId") Long groupId);
  
}
