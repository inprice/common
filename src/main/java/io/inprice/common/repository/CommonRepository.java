package io.inprice.common.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.jdbi.v3.core.Handle;

import io.inprice.common.info.ProductLink;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.ProductPrice;

public class CommonRepository {

  /**
   * Adjusts product and its links' positions and rankings.
   * In order to do that, investigates all the links under the product
   * and build a ProductPrice object up. With this object, two db operations
   * are consecutively executed: insert a new row into product_price and update product itself
   * 
   * @param transactional - db connection
   * @param id - product id
   * @param basePrice - product price
   * 
   * @return true if all the queries successfully executed
   */
  public static boolean adjustProductPrice(Handle transactional, Long id, BigDecimal basePrice, Long priceChangingLinkId) {
    CommonDao commonDao = transactional.attach(CommonDao.class);

    // links are fetched in price order (lowest comes first)
    List<ProductLink> activeLinks = commonDao.findProductLinkList(id, LinkStatus.AVAILABLE.name());

    ProductPrice prodPrice = null;
    if (activeLinks.size() > 0) {

      ProductLink plFirst = activeLinks.get(0);
      ProductLink plLast = activeLinks.get(activeLinks.size() - 1);

      prodPrice = new ProductPrice();
      prodPrice.setProductId(plFirst.getProductId());
      prodPrice.setPrice(basePrice);
      prodPrice.setLinks(activeLinks.size());
      prodPrice.setCompanyId(plFirst.getCompanyId());
      prodPrice.setMinPlatform(plFirst.getPlatform());
      prodPrice.setMinSeller(plFirst.getSeller());
      prodPrice.setMinPrice(plFirst.getPrice());
      prodPrice.setAvgPrice(basePrice);
      prodPrice.setMaxPlatform(plLast.getPlatform());
      prodPrice.setMaxSeller(plLast.getSeller());
      prodPrice.setMaxPrice(plLast.getPrice());
      prodPrice.setSuggestedPrice(basePrice);

      //finding total, ranking and rankingWith
      int ranking = 0;
      int rankingWith = 0;
      BigDecimal total = BigDecimal.ZERO;
      for (ProductLink pl: activeLinks) {
        total = total.add(pl.getPrice());
        if (ranking == 0 && basePrice.compareTo(pl.getPrice()) <= 0) {
          ranking = pl.getRanking();
        }
        if (basePrice.compareTo(pl.getPrice()) == 0) {
          rankingWith++;
        }
      }
      if (ranking == 0) {
        ranking = plLast.getRanking() + 1;
      }
      prodPrice.setRanking(ranking);
      prodPrice.setRankingWith(rankingWith);

      // finding avg basePrice
      if (total.compareTo(BigDecimal.ZERO) > 0) {
        prodPrice.setAvgPrice(total.divide(BigDecimal.valueOf(activeLinks.size()), 2, BigDecimal.ROUND_HALF_UP));
      }

      // setting diffs
      prodPrice.setMinDiff(findDiff(prodPrice.getPrice(), plFirst.getPrice()));
      prodPrice.setAvgDiff(findDiff(prodPrice.getPrice(), prodPrice.getAvgPrice()));
      prodPrice.setMaxDiff(findDiff(prodPrice.getPrice(), plLast.getPrice()));

      //finding product position
      if (basePrice.compareTo(prodPrice.getMinPrice()) <= 0) {
        prodPrice.setPosition(1);
        prodPrice.setMinPlatform("Yours");
        prodPrice.setMinSeller("You");
        prodPrice.setMinPrice(basePrice);
        prodPrice.setMinDiff(BigDecimal.ZERO);
      } else if (basePrice.compareTo(prodPrice.getAvgPrice()) < 0) {
        prodPrice.setPosition(2);
      } else if (basePrice.compareTo(prodPrice.getAvgPrice()) == 0) {
        prodPrice.setPosition(3);
      } else if (basePrice.compareTo(prodPrice.getMaxPrice()) < 0) {
        prodPrice.setPosition(4);
      } else {
        prodPrice.setPosition(5);
        prodPrice.setMaxPlatform("Yours");
        prodPrice.setMaxSeller("You");
        prodPrice.setMaxPrice(basePrice);
        prodPrice.setMaxDiff(BigDecimal.ZERO);
      }

    } 
    
    //updating product for new position and price_id
    boolean isOK = false;
    if (prodPrice != null) {
      long prodPriceId = commonDao.insertProductPrice(prodPrice);
      if (prodPriceId > 0) {
        isOK = commonDao.setProductPosition(id, prodPrice.getPosition(), prodPriceId);
        //updating each link for new position
        if (isOK) {
          adjustLinksPrices(commonDao, prodPrice, activeLinks, priceChangingLinkId);
        }
      }
    } else { // has no active link
      isOK = commonDao.zeroizeProductPrice(id);
    }

    return isOK;
  }

  private static void adjustLinksPrices(CommonDao commonDao, ProductPrice pp, List<ProductLink> activeLinks, Long priceChangingLinkId) {
    for (ProductLink pl: activeLinks) {
      int position = pl.getPosition();
      if (pl.getPrice().compareTo(pp.getMinPrice()) <= 0) {
        position = 1;
      } else if (pl.getPrice().compareTo(pp.getAvgPrice()) < 0) {
        position = 2;
      } else if (pl.getPrice().compareTo(pp.getAvgPrice()) == 0) {
        position = 3;
      } else if (pl.getPrice().compareTo(pp.getMaxPrice()) < 0) {
        position = 4;
      } else {
        position = 5;
      }

      if (position != pl.getPosition().intValue()) {
        commonDao.setLinkPosition(pl.getId(), position);
        if (priceChangingLinkId != null && priceChangingLinkId.equals(pl.getId())) {
          commonDao.insertLinkPrice(pl.getId(), pl.getPrice(), position, pl.getProductId(), pl.getCompanyId());
        }
      }
    }
  }

  private static BigDecimal findDiff(BigDecimal first, BigDecimal second) {
    BigDecimal BigDecimal_AHUNDRED = new BigDecimal(100);
    BigDecimal prodPrice = BigDecimal_AHUNDRED;
    if (first.compareTo(BigDecimal.ZERO) > 0 && second.compareTo(BigDecimal.ZERO) > 0) {
     prodPrice = second.divide(first, 4, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal_AHUNDRED).setScale(2);
    }
    return prodPrice;
  }

}
