package io.inprice.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.info.ProductLink;
import io.inprice.common.mappers.ProductLinkMapper;
import io.inprice.common.models.ProductPrice;

public interface CommonDao {
  
  @SqlQuery(
    "select l.id, s.domain as platform, seller, price, position, product_id, company_id, dense_rank() over (order by price) as ranking from link as l " +
    "inner join site as s on s.id = site_id " +
    "where price > 0 " +
    "  and status = :status " +
    "  and product_id = :productId " +
    "order by price"
  )
  @UseRowMapper(ProductLinkMapper.class)
  List<ProductLink> findProductLinkList(@Bind("productId") Long productId, @Bind("status") String status);

  @SqlUpdate(
    "update product " +
    "set position=:position, last_price_id=:lastPriceId, updated_at=now() " +
    "where id=:id "
  )
  boolean setProductPosition(@Bind("id") long id, @Bind("position") int position, @Bind("lastPriceId") long lastPriceId);

  @SqlUpdate(
    "insert into product_price " +
    "(product_id, price, min_platform, min_seller, min_price, min_diff, avg_price, avg_diff, " +
      "max_platform, max_seller, max_price, max_diff, links, position, ranking, ranking_with, suggested_price, company_id) " + 
    "values "+
    "(pp:productId, pp:price, pp:minPlatform, pp:minSeller, pp:minPrice, pp:minDiff, pp:avgPrice, pp:avgDiff, " +
     "pp:maxPlatform, pp:maxSeller, pp:maxPrice, pp:maxDiff, pp:links, pp:position, pp:ranking, pp:rankingWith, pp:suggestedPrice, pp:companyId)"
  )
  @GetGeneratedKeys("id")
  long insertProductPrice(@BindBean("pp") ProductPrice productPrice);

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
