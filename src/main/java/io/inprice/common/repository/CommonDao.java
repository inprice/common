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
		"select l.*, p.alarm_id as product_alarm_id, p.smart_price_id as product_smart_price_id from link as l " +
		"inner join product as p on p.id = l.product_id " +
		"inner join workspace as a on a.id = l.workspace_id " + 
		"where a.status in ('FREE', 'VOUCHERED', 'SUBSCRIBED') " +
		"  and l.url_hash=:urlHash " +
		"  and l.retry < 3"
	)
  @UseRowMapper(LinkMapper.class)
  List<Link> findAllLinksByHash(@Bind("urlHash") String urlHash);

  @SqlUpdate("update link set checked_at = now() where id=:id")
  boolean refreshCheckedAt(@Bind("id") long id); 

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, old_price, new_price, diff_amount, diff_rate, product_id, workspace_id) "+
    "values (:linkId, :oldPrice, :newPrice, :diffAmount, :diffRate, :productId, :workspaceId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, 
		@Bind("oldPrice") BigDecimal oldPrice, @Bind("newPrice") BigDecimal newPrice, 
    @Bind("diffAmount") BigDecimal diffAmount, @Bind("diffRate") BigDecimal diffRate,
    @Bind("productId") long productId, @Bind("workspaceId") long workspaceId);

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, new_price, product_id, workspace_id) "+
    "values (:linkId, :newPrice, :productId, :workspaceId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, @Bind("newPrice") BigDecimal newPrice, 
  		@Bind("productId") long productId, @Bind("workspaceId") long workspaceId);

  @SqlCall("call sp_refresh_product(:productId, :productPrice, :minPrice, :avgPrice, :maxPrice, :position, :alarmId, :smartPriceId, :actives)")
  @OutParameter(name="productPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="minPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="avgPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="maxPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="position", sqlType=Types.VARCHAR)
  @OutParameter(name="alarmId", sqlType=Types.BIGINT)
  @OutParameter(name="smartPriceId", sqlType=Types.BIGINT)
  @OutParameter(name="actives", sqlType=Types.INTEGER)
  OutParameters refreshProduct(@Bind("productId") Long productId);

}
