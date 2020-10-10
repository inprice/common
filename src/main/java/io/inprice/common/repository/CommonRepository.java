package io.inprice.common.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.jdbi.v3.core.Handle;

import io.inprice.common.info.ProductLink;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.ProductPrice;

public class CommonRepository {

  public ProductPrice getProductPrice(Handle handle, Long productId) {
    ProductPrice result = null;

    CommonDao commonDao = handle.attach(CommonDao.class);

    List<ProductLink> prodLinkList = commonDao.getProductLinkList(productId, LinkStatus.AVAILABLE);
    if (prodLinkList.size() > 0) {

      BigDecimal productPrice = commonDao.getProductPrice(productId);
      if (productPrice != null) {

        ProductLink plFirst = prodLinkList.get(0);
        ProductLink plLast = prodLinkList.get(prodLinkList.size() - 1);

        result = new ProductPrice();
        result.setProductId(plFirst.getProductId());
        result.setPrice(productPrice);
        result.setLinks(prodLinkList.size());
        result.setCompanyId(plFirst.getCompanyId());
        result.setMinPlatform(plFirst.getSiteName());
        result.setMinSeller(plFirst.getSeller());
        result.setMinPrice(plFirst.getPrice());
        result.setAvgPrice(productPrice);
        result.setMaxPlatform(plLast.getSiteName());
        result.setMaxSeller(plLast.getSeller());
        result.setMaxPrice(plLast.getPrice());
        result.setSuggestedPrice(productPrice);

        //finding total, ranking and rankingWith
        int ranking = 0;
        int rankingWith = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (ProductLink pl: prodLinkList) {
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
        result.setRanking(ranking);
        result.setRankingWith(rankingWith);

        // finding avg price
        if (total.compareTo(BigDecimal.ZERO) > 0) {
          result.setAvgPrice(total.divide(BigDecimal.valueOf(prodLinkList.size()), 2, BigDecimal.ROUND_HALF_UP));
        }

        // setting diffs
        result.setMinDiff(findDiff(result.getPrice(), plFirst.getPrice()));
        result.setAvgDiff(findDiff(result.getPrice(), result.getAvgPrice()));
        result.setMaxDiff(findDiff(result.getPrice(), plLast.getPrice()));

        //finding product position
        if (productPrice.compareTo(result.getMinPrice()) <= 0) {
          result.setPosition(1);
          result.setMinPlatform("Yours");
          result.setMinSeller("You");
          result.setMinPrice(productPrice);
          result.setMinDiff(BigDecimal.ZERO);
        } else if (productPrice.compareTo(result.getAvgPrice()) < 0) {
          result.setPosition(2);
        } else if (productPrice.compareTo(result.getAvgPrice()) == 0) {
          result.setPosition(3);
        } else if (productPrice.compareTo(result.getMaxPrice()) < 0) {
          result.setPosition(4);
        } else {
          result.setPosition(5);
          result.setMaxPlatform("Yours");
          result.setMaxSeller("You");
          result.setMaxPrice(productPrice);
          result.setMaxDiff(BigDecimal.ZERO);
        }
        result.setProdCompList(prodLinkList);
      }
    }

    return result;
  }

  private BigDecimal findDiff(BigDecimal first, BigDecimal second) {
    BigDecimal BigDecimal_AHUNDRED = new BigDecimal(100);
    BigDecimal result = BigDecimal_AHUNDRED;
    if (first.compareTo(BigDecimal.ZERO) > 0 && second.compareTo(BigDecimal.ZERO) > 0) {
     result = second.divide(first, 4, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal_AHUNDRED).setScale(2);
    }
    return result;
  }

  public boolean updatePrice(Handle handle, List<ProductPrice> ppList, List<Long> zeroizeIdList) {
    CommonDao commonDao = handle.attach(CommonDao.class);

    int aff3 = 0;

    if (ppList != null) {
      for (ProductPrice pp : ppList) {

        int aff1 = 
          commonDao.updateProductPrice(
            pp.getProductId(), 
            pp.getPrice(), 
            pp.getPosition(), 
            commonDao.insertProductPrice(pp)
          );

        if (aff1 > 0) {
          //if any link position changed then review and update its all links' positions accordingly
          for (ProductLink pl: pp.getProdCompList()) {
            int newPosition = pl.getPosition();
            if (pl.getPrice().compareTo(pp.getMinPrice()) <= 0) {
              newPosition = 1;
            } else if (pl.getPrice().compareTo(pp.getAvgPrice()) < 0) {
              newPosition = 2;
            } else if (pl.getPrice().compareTo(pp.getAvgPrice()) == 0) {
              newPosition = 3;
            } else if (pl.getPrice().compareTo(pp.getMaxPrice()) < 0) {
              newPosition = 4;
            } else {
              newPosition = 5;
            }

            if (newPosition != pl.getPosition().intValue()) {
              int aff2 = commonDao.setPosition(pl.getId(), newPosition);

              if (aff2 > 0) {
                aff3 = 
                  commonDao.insertLinkPrice(
                    pl.getId(),
                    pl.getPrice(),
                    newPosition,
                    pl.getProductId(),
                    pl.getCompanyId()
                  );
              }
            }
          }
        }
      }
    }

    if (zeroizeIdList != null && zeroizeIdList.size() > 0) {
      aff3 = commonDao.zeroizePositions(zeroizeIdList);
    }

    return aff3 > 0;
  }

}
