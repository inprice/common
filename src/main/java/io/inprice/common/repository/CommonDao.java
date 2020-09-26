package io.inprice.common.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import io.inprice.common.info.ProductLink;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.ProductPrice;

public interface CommonDao {
  
  @SqlQuery(
    "select id, product_id, price, position, seller, company_id, s.domain as site_name, dense_rank() over (order by price) as ranking " +
    "from link " +
    "inner join site as s on s.id = site_id " +
    "where product_id = :productId " +
    "  and status = :status " +
    "  and price > 0 " +
    "order by price"
  )
  List<ProductLink> getProductLinkList(@Bind Long productId, @Bind LinkStatus status);

  @SqlQuery("select price from product where id=:id")
  BigDecimal getProductPrice(@Bind long id);

  @SqlUpdate(
    "insert into product_price " +
    "(product_id, price, min_platform, min_seller, min_price, min_diff, avg_price, avg_diff, " +
      "max_platform, max_seller, max_price, max_diff, links, position, ranking, ranking_with, suggested_price, company_id) " + 
    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
  )
  @GetGeneratedKeys("id")
  long insertProductPrice(@BindBean ProductPrice productPrice);

  @SqlUpdate(
    "update product " +
    "set price=:price, position=:position, last_price_id=:lastPriceId, updated_at=now() " +
    "where id=? "
  )
  int updateProductPrice(@Bind long id, @Bind BigDecimal price, @Bind int position, @Bind long lastPriceId);

  @SqlUpdate("update product set position=3, last_price_id=null, updated_at=now() where id in (<idList>)")
  int zeroizePositions(@BindList List<Long> idList);

  @SqlUpdate("update link set position=:position, last_update=now() where id=:id")
  int setPosition(@Bind long id, @Bind int position);

  @SqlUpdate(
    "insert into link_price " +
    "(link_id, price, position, product_id, company_id) values (:linkId, :price, :position, :productId, :companyId)"
  )
  int insertLinkPrice(@Bind long linkId, @Bind BigDecimal price, @Bind int position, @Bind long productId, @Bind long companyId);

}
