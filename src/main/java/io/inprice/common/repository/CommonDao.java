package io.inprice.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.info.ProductLink;
import io.inprice.common.mappers.LinkPriceMapper;
import io.inprice.common.mappers.ProductLinkMapper;
import io.inprice.common.models.LinkPrice;
import io.inprice.common.models.Product;

public interface CommonDao {

  @SqlQuery(
    "select l.id, plt.domain as platform, l.seller, l.price, l.position, p.price as product_price, " +
    "l.product_id, l.account_id, dense_rank() over (order by l.price) as ranking from link as l " +
    "inner join product as p on p.id = l.product_id " +
    "left join platform as plt on plt.id = l.platform_id " + 
    "where l.price > 0 " +
    "  and l.status = :status " +
    "  and l.product_id = :productId " +
    "order by l.price"
  )
  @UseRowMapper(ProductLinkMapper.class)
  List<ProductLink> findProductLinkList(@Bind("productId") Long productId, @Bind("status") String status);

  @SqlUpdate(
    "update product " +
    "set price=:sample.price, updated_at=now(), " +
    " min_platform=:sample.minPlatform, min_seller=:sample.minSeller, min_price=:sample.minPrice, min_diff=:sample.minDiff, " +
    " avg_price=:sample.avgPrice, avg_diff=:sample.avgDiff, " +
    " max_platform=:sample.maxPlatform, max_seller=:sample.maxSeller, max_price=:sample.maxPrice, max_diff=:sample.maxDiff, " +
    " position=:sample.position, link_count=:sample.linkCount, ranking=:sample.ranking, ranking_with=:sample.rankingWith, " +
    " suggested_price=:sample.suggestedPrice, account_id=:sample.accountId " + 
    "where id=:sample.id "
  )
  boolean udpateProductPrice(@BindBean("sample") Product sample);

  @SqlUpdate(
    "update product " +
    "set position=3, link_count=0, ranking=0, ranking_with=0, suggested_price=price, updated_at=now(), " +
    " min_platform=null, min_seller=null, min_price=0, min_diff=0, " +
    " avg_price=0, avg_diff=0, " +
    " max_platform=null, max_seller=null, max_price=0, max_diff=0 " +
    "where id=:id "
  )
  boolean zeroizeProductPrice(@Bind("id") Long id);

  @SqlUpdate("update link set position=:position where id=:id")
  boolean setLinkPosition(@Bind("id") long id, @Bind("position") int position);

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
    "(link_id, price, position, diff_amount, diff_rate, product_id, account_id) "+
    "values (:linkId, :price, :position, :diffAmount, :diffRate, :productId, :accountId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, @Bind("price") BigDecimal price, 
    @Bind("position") int position, @Bind("diffAmount") BigDecimal diffAmount, @Bind("diffRate") BigDecimal diffRate,
    @Bind("productId") long productId, @Bind("accountId") long accountId);

}
