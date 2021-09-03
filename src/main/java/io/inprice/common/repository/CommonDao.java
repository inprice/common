package io.inprice.common.repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;

import org.jdbi.v3.core.statement.OutParameters;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.OutParameter;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.LinkMapper;
import io.inprice.common.models.Link;

public interface CommonDao {

  @SqlQuery(
		"select l.* from link as l " +
		"inner join account as a on a.id = l.account_id " + 
		"where a.status in ('FREE', 'COUPONED', 'SUBSCRIBED') " +
		"  and l.url_hash=:urlHash " +
		"  and l.retry < 3"
	)
  @UseRowMapper(LinkMapper.class)
  List<Link> findActiveLinksByHash(@Bind("urlHash") String urlHash);

  @SqlUpdate("update link set checked_at = now() where id=:id")
  boolean refreshCheckedAt(@Bind("id") long id); 

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, old_price, new_price, diff_amount, diff_rate, group_id, account_id) "+
    "values (:linkId, :oldPrice, :newPrice, :diffAmount, :diffRate, :groupId, :accountId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, 
		@Bind("oldPrice") BigDecimal oldPrice, @Bind("newPrice") BigDecimal newPrice, 
    @Bind("diffAmount") BigDecimal diffAmount, @Bind("diffRate") BigDecimal diffRate,
    @Bind("groupId") long groupId, @Bind("accountId") long accountId);

  @SqlCall("call sp_refresh_group(:groupId, :minPrice, :avgPrice, :maxPrice, :total, :level, :alarmId)")
  @OutParameter(name="minPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="avgPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="maxPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="total", sqlType=Types.DOUBLE)
  @OutParameter(name="level", sqlType=Types.VARCHAR)
  @OutParameter(name="alarmId", sqlType=Types.BIGINT)
  OutParameters refreshGroup(@Bind("groupId") Long groupId);

}
