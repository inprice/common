package io.inprice.common.repository;

import java.math.BigDecimal;
import java.sql.Types;

import org.jdbi.v3.core.statement.OutParameters;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.OutParameter;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.LinkPriceMapper;
import io.inprice.common.models.LinkPrice;

public interface CommonDao {

  @SqlQuery(
    "select * from link_price " +
    "where link_id = :linkId " +
    "  and price > 0 " +
    "order by id desc " +
    "limit 1"
  )
  @UseRowMapper(LinkPriceMapper.class)
  LinkPrice findLastPriceTransOfLink(@Bind("linkId") Long linkId);

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, price, diff_amount, diff_rate, group_id, account_id) "+
    "values (:linkId, :price, :diffAmount, :diffRate, :groupId, :accountId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, @Bind("price") BigDecimal price, 
    @Bind("diffAmount") BigDecimal diffAmount, @Bind("diffRate") BigDecimal diffRate,
    @Bind("groupId") long groupId, @Bind("accountId") long accountId);

  @SqlCall("call sp_refresh_group(:groupId, :minPrice, :avgPrice, :maxPrice, :total, :level)")
  @OutParameter(name="minPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="avgPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="maxPrice", sqlType=Types.DOUBLE)
  @OutParameter(name="total", sqlType=Types.DOUBLE)
  @OutParameter(name="level", sqlType=Types.VARCHAR)
  OutParameters refreshGroup(@Bind("groupId") Long groupId);

}
