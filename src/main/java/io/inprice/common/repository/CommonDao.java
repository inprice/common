package io.inprice.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.info.ProductLink;
import io.inprice.common.mappers.ProductLinkMapper;
import io.inprice.common.models.Product;

public interface CommonDao {
  
  @SqlQuery(
    "select l.id, s.domain as platform, seller, price, position, product_id, company_id, dense_rank() over (order by price) as ranking from link as l " +
    "inner join site as s on s.id = site_id " +
    "where price > 0 " +
    "  and l.status = :status " +
    "  and product_id = :productId " +
    "order by price"
  )
  @UseRowMapper(ProductLinkMapper.class)
  List<ProductLink> findProductLinkList(@Bind("productId") Long productId, @Bind("status") String status);

  @SqlUpdate(
    "update product " +
    "set price=:sample.price, updated_at=now(), " +
    " min_platform=:sample.minPlatform, min_seller=:sample.minSeller, min_price=:sample.minPrice, min_diff=:sample.minDiff, " +
    " avg_price=:sample.avgPrice, avg_diff=:sample.avgDiff, " +
    " max_platform=:sample.maxPlatform, max_seller=:sample.maxSeller, max_price=:sample.maxPrice, max_diff=:sample.maxDiff, " +
    " position=:sample.position, ranking=:sample.ranking, ranking_with=:sample.rankingWith, suggested_price=:sample.suggestedPrice, company_id=:sample.companyId " + 
    "where id=:sample.id "
  )
  boolean udpateProductPrice(@BindBean("sample") Product sample);

  @SqlUpdate("update product set position=3, last_price_id=null, updated_at=now() where id:id")
  boolean zeroizeProductPrice(@Bind("id") Long id);

  @SqlUpdate("update link set position=:position where id=:id")
  boolean setLinkPosition(@Bind("id") long id, @Bind("position") int position);

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, price, position, product_id, company_id) values (:linkId, :price, :position, :productId, :companyId)"
  )
  boolean insertLinkPrice(@Bind("linkId") long linkId, @Bind("price") BigDecimal price, 
    @Bind("position") int position, @Bind("productId") long productId, @Bind("companyId") long companyId);

}
