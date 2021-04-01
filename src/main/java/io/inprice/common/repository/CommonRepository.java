package io.inprice.common.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Handle;

import io.inprice.common.dto.LinkSummary;
import io.inprice.common.meta.Level;
import io.inprice.common.models.LinkGroup;
import io.inprice.common.models.LinkPrice;

public class CommonRepository {

	public static boolean refreshGroup(Handle transaction, Long groupId) {
		return refreshGroup(transaction, groupId, null);
	}
	
  /**
   * Calculates group fields.
   * 
   * In order to do that, scans all the links under the group
   * and build a sample LinkGroup object.
   * 
   * @return true if all the queries successfully executed
   */
  public static boolean refreshGroup(Handle transaction, Long groupId, Long priceChangingLinkId) {
    CommonDao commonDao = transaction.attach(CommonDao.class);

    //links are order in status and price (lowest comes first)
    List<LinkSummary> links = commonDao.findLinksByGroupId(groupId);
    List<LinkSummary> activeLinks = null;

    int actives = 0;
    int waitings = 0;
  	int tryings = 0;
  	int problems = 0;
  	BigDecimal grandTotal = BigDecimal.ZERO;

    LinkGroup sample = new LinkGroup();
    sample.setId(groupId);

    if (links.size() > 0) {
    	LinkSummary first = null;
      LinkSummary last = null;
      BigDecimal total = BigDecimal.ZERO;
      
      activeLinks = new ArrayList<>();

      for (LinkSummary link: links) {

      	switch (link.getStatus().getGroup()) {
  				case ACTIVE: {
        		actives++;
        		if (first == null || link.getPrice().compareTo(first.getPrice()) < 0) first = link;
        		if (last == null || link.getPrice().compareTo(last.getPrice()) > 0) last = link;
        		total = total.add(link.getPrice());
        		activeLinks.add(link);
  					break;
  				}
  				case WAITING: {
  					waitings++;
  					break;
  				}
  				case TRYING: {
  					tryings++;
  					break;
  				}
  				case PROBLEM: {
  					problems++;
  					break;
  				}
				}
      	grandTotal = grandTotal.add(link.getPrice());
      }

      if (actives > 0) {
      	BigDecimal groupPrice = first.getGroupPrice();
      	
        sample.setActives(actives);
        sample.setWaitings(waitings);
        sample.setTryings(tryings);
        sample.setProblems(problems);
        sample.setRanking(0);
        sample.setLevel(Level.UNSPECIFIED);
        sample.setPrice(groupPrice);
        sample.setTotal(total);
        sample.setAccountId(first.getAccountId());
        sample.setMinPlatform(first.getPlatform());
        sample.setMinSeller(first.getSeller());
        sample.setMinPrice(first.getPrice());
        sample.setAvgPrice(total.divide(BigDecimal.valueOf(actives), 2, BigDecimal.ROUND_HALF_UP));
        sample.setMaxPlatform(last.getPlatform());
        sample.setMaxSeller(last.getSeller());
        sample.setMaxPrice(last.getPrice());

        if (groupPrice.compareTo(BigDecimal.ZERO) > 0) {
          sample.setDiffMin(findDiff(first.getPrice(), groupPrice));
          sample.setDiffAvg(findDiff(sample.getAvgPrice(), groupPrice));
          sample.setDiffMax(findDiff(last.getPrice(), groupPrice));

          if (groupPrice.compareTo(sample.getMinPrice()) <= 0) {
            sample.setMinPlatform("Yours");
            sample.setMinSeller("You");
            sample.setMinPrice(groupPrice);
            sample.setDiffMin(BigDecimal.ZERO);
            sample.setRanking(1);
            sample.setLevel(Level.LOWEST);
          } else if (groupPrice.compareTo(sample.getAvgPrice()) < 0) {
          	sample.setLevel(Level.LOWER);
          } else if (groupPrice.compareTo(sample.getAvgPrice()) == 0) {
          	sample.setLevel(Level.AVERAGE);
          } else if (groupPrice.compareTo(sample.getMaxPrice()) < 0) {
          	sample.setLevel(Level.HIGHER);
          } else if (groupPrice.compareTo(sample.getMaxPrice()) >= 0) {
            sample.setMaxPlatform("Yours");
            sample.setMaxSeller("You");
            sample.setMaxPrice(groupPrice);
            sample.setDiffMax(BigDecimal.ZERO);
            sample.setRanking(actives+1);
            sample.setLevel(Level.HIGHEST);
          }

        	//finding ranking (activeLinks are in price order)
          if (sample.getRanking().intValue() == 0) { //which means not set!
          	for (int i = 0; i < activeLinks.size(); i++) {
  						LinkSummary link = activeLinks.get(i);
  						if (groupPrice.compareTo(link.getPrice()) >= 0) sample.setRanking(i+1);
          	}
          }
        }
      }
    }

    boolean isOK = commonDao.udpateGroup(sample, grandTotal);
    if (isOK && actives > 0) {
      isOK = refreshLinks(commonDao, sample, activeLinks, priceChangingLinkId);
    }

    return isOK;
  }

  private static boolean refreshLinks(CommonDao commonDao, LinkGroup sample, List<LinkSummary> activeLinks, Long priceChangingLinkId) {
  	for (int ranking = 1; ranking <= activeLinks.size(); ranking++) {
  		LinkSummary link = activeLinks.get(ranking-1);

			Level level = link.getLevel();
      if (link.getPrice().compareTo(sample.getMinPrice()) <= 0) {
      	level = Level.LOWEST;
      } else if (link.getPrice().compareTo(sample.getAvgPrice()) < 0) {
      	level = Level.LOWER;
      } else if (link.getPrice().compareTo(sample.getAvgPrice()) == 0) {
      	level = Level.AVERAGE;
      } else if (link.getPrice().compareTo(sample.getMaxPrice()) < 0) {
      	level = Level.HIGHER;
      } else {
      	level = Level.HIGHEST;
      }

      if (level.equals(link.getLevel()) == false || link.getRanking() != ranking) {
    		commonDao.setLinkLevelAndRanking(link.getId(), level, ranking);
      }

       // priceChangingLinkId comes from StatusChangingLinksConsumer which can be found in Manager
      if (priceChangingLinkId != null && priceChangingLinkId.equals(link.getId())) {

        BigDecimal diffAmount = BigDecimal.ZERO;
        BigDecimal diffRate = BigDecimal.ZERO;
  
        // in order to find the difference old and new prices, we need to find the last trans from db
        LinkPrice lastPriceTrans = commonDao.findLastPriceTransOfLink(link.getId());
        if (lastPriceTrans != null) {
          BigDecimal lastPrice = lastPriceTrans.getPrice();
          if (lastPrice.compareTo(link.getPrice()) != 0) {
            diffAmount = lastPrice.subtract(link.getPrice());
            diffRate = findDiff(lastPrice, link.getPrice());
          }
        }

        commonDao.insertLinkPrice(link.getId(), link.getPrice(), level, diffAmount, diffRate, sample.getId(), sample.getAccountId());
      }

    }
    return true;
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
