package io.inprice.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.dto.LinkSummary;
import io.inprice.common.dto.LinkSummaryMapper;
import io.inprice.common.mappers.LinkPriceMapper;
import io.inprice.common.meta.Level;
import io.inprice.common.models.LinkGroup;
import io.inprice.common.models.LinkPrice;

public interface CommonDao {

  @SqlQuery(
    "select l.id, l.status, p.domain as platform, l.seller, l.price, l.ranking, l.level, l.account_id, g.price as group_price from link as l " +
		"inner join link_group as g on g.id = l.group_id " + 
    "inner join platform as p on p.id = l.platform_id " + 
    "where l.group_id = :groupId " +
    "order by l.price" //ordering is important
  )
  @UseRowMapper(LinkSummaryMapper.class)
  List<LinkSummary> findLinksByGroupId(@Bind("groupId") Long groupId);

  @SqlUpdate(
    "update link_group " +
    "set level=:sample.level, ranking=:sample.ranking, total=:grandTotal, updated_at=now(), " +
    " actives=:sample.actives, waitings=:sample.waitings, tryings=:sample.tryings, problems=:sample.problems, " +
    " min_platform=:sample.minPlatform, min_seller=:sample.minSeller, min_price=:sample.minPrice, diff_min=:sample.diffMin, " +
    " avg_price=:sample.avgPrice, diff_avg=:sample.diffAvg, " +
    " max_platform=:sample.maxPlatform, max_seller=:sample.maxSeller, max_price=:sample.maxPrice, diff_max=:sample.diffMax " +
    "where id=:sample.id "
  )
  boolean udpateGroup(@BindBean("sample") LinkGroup sample, @Bind("grandTotal") BigDecimal grandTotal);
  
  @SqlUpdate("update link set level=:level, ranking=:ranking where id=:id")
  boolean setLinkLevelAndRanking(@Bind("id") long id, @Bind("level") Level level, @Bind("ranking") int ranking);

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
    "(link_id, price, level, diff_amount, diff_rate, group_id, account_id) "+
    "values (:linkId, :price, :level, :diffAmount, :diffRate, :groupId, :accountId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, @Bind("price") BigDecimal price, 
    @Bind("level") Level level, @Bind("diffAmount") BigDecimal diffAmount, @Bind("diffRate") BigDecimal diffRate,
    @Bind("groupId") long groupId, @Bind("accountId") long accountId);

}
