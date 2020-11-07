package io.inprice.common.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.jdbi.v3.core.Handle;

import io.inprice.common.info.ProductLink;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.Product;

public class CommonRepository {

  /**
   * Adjusts product and its links' positions and rankings.
   * In order to do that, investigates all the links under the product
   * and build a ProductPrice object up. With this object, two db operations
   * are consecutively executed: insert a new row into product_price and update product itself
   * 
   * @param transactional
   * @param productId
   * @param productPrice
   * 
   * @return true if all the queries successfully executed
   */
  public static boolean adjustProductPrice(Handle transactional, Long productId, Long priceChangingLinkId) {
    CommonDao commonDao = transactional.attach(CommonDao.class);

    // links are fetched in price order (lowest comes first)
    List<ProductLink> activeLinks = commonDao.findProductLinkList(productId, LinkStatus.AVAILABLE.name());

    Product sample = null;
    if (activeLinks.size() > 0) {

      ProductLink plFirst = activeLinks.get(0);
      ProductLink plLast = activeLinks.get(activeLinks.size() - 1);
      BigDecimal productPrice = plFirst.getProductPrice();

      sample = new Product();
      sample.setId(productId);
      sample.setPrice(productPrice);
      sample.setCompanyId(plFirst.getCompanyId());
      sample.setMinPlatform(plFirst.getPlatform());
      sample.setMinSeller(plFirst.getSeller());
      sample.setMinPrice(plFirst.getPrice());
      sample.setAvgPrice(productPrice);
      sample.setMaxPlatform(plLast.getPlatform());
      sample.setMaxSeller(plLast.getSeller());
      sample.setMaxPrice(plLast.getPrice());
      sample.setSuggestedPrice(productPrice);

      //finding total, ranking and rankingWith
      int ranking = 0;
      int rankingWith = 0;
      BigDecimal total = BigDecimal.ZERO;
      for (ProductLink pl: activeLinks) {
        total = total.add(pl.getPrice());
        if (ranking == 0 && productPrice.compareTo(pl.getPrice()) <= 0) {
          ranking = pl.getRanking();
        }
        if (productPrice.compareTo(pl.getPrice()) == 0) {
          rankingWith++;
        }
      }
      if (ranking == 0) {
        ranking = plLast.getRanking() + 1;
      }
      sample.setRanking(ranking);
      sample.setRankingWith(rankingWith);

      // finding avg productPrice
      if (total.compareTo(BigDecimal.ZERO) > 0) {
        sample.setAvgPrice(total.divide(BigDecimal.valueOf(activeLinks.size()), 2, BigDecimal.ROUND_HALF_UP));
      }

      // setting diffs
      sample.setMinDiff(findDiff(sample.getPrice(), plFirst.getPrice()));
      sample.setAvgDiff(findDiff(sample.getPrice(), sample.getAvgPrice()));
      sample.setMaxDiff(findDiff(sample.getPrice(), plLast.getPrice()));

      //finding product position
      if (productPrice.compareTo(sample.getMinPrice()) <= 0) {
        sample.setPosition(1);
        sample.setMinPlatform("Yours");
        sample.setMinSeller("You");
        sample.setMinPrice(productPrice);
        sample.setMinDiff(BigDecimal.ZERO);
      } else if (productPrice.compareTo(sample.getAvgPrice()) < 0) {
        sample.setPosition(2);
      } else if (productPrice.compareTo(sample.getAvgPrice()) == 0) {
        sample.setPosition(3);
      } else if (productPrice.compareTo(sample.getMaxPrice()) < 0) {
        sample.setPosition(4);
      } else {
        sample.setPosition(5);
        sample.setMaxPlatform("Yours");
        sample.setMaxSeller("You");
        sample.setMaxPrice(productPrice);
        sample.setMaxDiff(BigDecimal.ZERO);
      }

    } 
    
    //updating product for new position and price_id
    boolean isOK = false;
    if (sample != null) {
      isOK = commonDao.udpateProductPrice(sample);
      if (isOK) { //updating each link for new position
        adjustLinksPrices(commonDao, sample, activeLinks, priceChangingLinkId);
      }
    } else { // has no active link
      isOK = commonDao.zeroizeProductPrice(productId);
    }

    return isOK;
  }

  private static void adjustLinksPrices(CommonDao commonDao, Product sample, List<ProductLink> activeLinks, Long priceChangingLinkId) {
    for (ProductLink pl: activeLinks) {
      int position = pl.getPosition();
      if (pl.getPrice().compareTo(sample.getMinPrice()) <= 0) {
        position = 1;
      } else if (pl.getPrice().compareTo(sample.getAvgPrice()) < 0) {
        position = 2;
      } else if (pl.getPrice().compareTo(sample.getAvgPrice()) == 0) {
        position = 3;
      } else if (pl.getPrice().compareTo(sample.getMaxPrice()) < 0) {
        position = 4;
      } else {
        position = 5;
      }

      boolean isPositionChanged = (position != pl.getPosition().intValue());

      if (isPositionChanged) {
        commonDao.setLinkPosition(pl.getId(), position);
        commonDao.deleteLastLinkPriceRow(pl.getId()); // the most recent one must be deleted so that a new one replace it correctly
      }

      // this info comes from StatusChangingLinksConsumer in Manager
      if (isPositionChanged || (priceChangingLinkId != null && priceChangingLinkId.equals(pl.getId()))) {
        commonDao.insertLinkPrice(pl.getId(), pl.getPrice(), position, pl.getProductId(), pl.getCompanyId());
      }
    }
  }

  private static BigDecimal findDiff(BigDecimal first, BigDecimal second) {
    BigDecimal BigDecimal_AHUNDRED = new BigDecimal(100);
    BigDecimal sample = BigDecimal_AHUNDRED;
    if (first.compareTo(BigDecimal.ZERO) > 0 && second.compareTo(BigDecimal.ZERO) > 0) {
     sample = second.divide(first, 4, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal_AHUNDRED).setScale(2);
    }
    return sample;
  }

}
