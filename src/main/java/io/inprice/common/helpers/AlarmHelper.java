package io.inprice.common.helpers;

import java.math.BigDecimal;
import java.util.Date;

import io.inprice.common.info.ProductRefreshResult;
import io.inprice.common.meta.AlarmSubject;
import io.inprice.common.models.Alarm;
import io.inprice.common.models.Link;
import io.inprice.common.models.Product;
import io.inprice.common.utils.DateUtils;

/**
 * Generates alarm update queries for products and links
 * 
 * @author mdpinar
 * @since 2021-11-19
 */
public class AlarmHelper {

  public static String generateAlarmUpdateQueryForProduct(Product oldProduct, ProductRefreshResult prr, Alarm alarm) {
  	boolean willBeUpdated = false;

  	if (AlarmSubject.POSITION.equals(alarm.getSubject())) {
  		switch (alarm.getSubjectWhen()) {
  			case EQUAL: {
  				willBeUpdated = prr.getPosition().name().equals(alarm.getCertainPosition());
  				break;
  			}
  			case NOT_EQUAL: {
  				willBeUpdated = prr.getPosition().name().equals(alarm.getCertainPosition()) == false;
  				break;
  			}
  			default: {
  				willBeUpdated = true;
  				break;
  			}
			}
  	}

  	BigDecimal oldAmount = null;
  	BigDecimal newAmount = null;

  	if (AlarmSubject.POSITION.equals(alarm.getSubject()) == false) {
  		switch (alarm.getSubject()) {
  			case MINIMUM: {
  				oldAmount = oldProduct.getMinPrice();
  				newAmount = prr.getMinPrice();
  				break;
  			}
  			case AVERAGE: {
  				oldAmount = oldProduct.getAvgPrice();
  				newAmount = prr.getAvgPrice();
  				break;
  			}
  			case MAXIMUM: {
  				oldAmount = oldProduct.getMaxPrice();
  				newAmount = prr.getMaxPrice();
  				break;
  			}
				default: break;
			}

  		if (newAmount != null && oldAmount != null && newAmount.compareTo(oldAmount) != 0) {
    		switch (alarm.getSubjectWhen()) {
    			case INCREASED: {
    				willBeUpdated = newAmount.compareTo(oldAmount) > 0;
    				break;
    			}
    			case DECREASED: {
    				willBeUpdated = newAmount.compareTo(oldAmount) < 0;
    				break;
    			}
    			case OUT_OF_LIMITS: {
  					willBeUpdated = checkIfPriceIsOutOfLimits(newAmount, alarm.getAmountLowerLimit(), alarm.getAmountUpperLimit());
    				break;
    			}
    			default: {
    				willBeUpdated = true;
    				break;
    			}
    		}
  		}
  	}

  	if (willBeUpdated) {
  		String tobeAlarmedPart = "tobe_alarmed=true, ";

    	//checks if it is already notified within 10 mins. if so, no need to disturb the customer!
    	if (willBeUpdated && oldProduct.getAlarmedAt() != null) {
        long diff = DateUtils.findMinuteDiff(oldProduct.getAlarmedAt(), new Date());
        if (diff <= 10) {
        	tobeAlarmedPart = "";
        }
    	}

  		return "update product set "+tobeAlarmedPart+" updated_at=now() where id=" + oldProduct.getId();
  	}

  	return null;
  }

  public static String generateAlarmUpdateQueryForLink(Link linkFromDb, Link linkFromParser, Alarm alarm) {
  	boolean willBeUpdated = false;

  	if (AlarmSubject.POSITION.equals(alarm.getSubject()) && linkFromDb.getGrup().equals(linkFromParser.getGrup()) == false) {
  		switch (alarm.getSubjectWhen()) {
  			case EQUAL: {
  				willBeUpdated = linkFromParser.getGrup().name().equals(alarm.getCertainPosition());
  				break;
  			}
  			case NOT_EQUAL: {
  				willBeUpdated = linkFromParser.getGrup().name().equals(alarm.getCertainPosition()) == false;
  				break;
  			}
  			default: {
  				willBeUpdated = true;
  				break;
  			}
			}
  	}
  	
  	if (AlarmSubject.PRICE.equals(alarm.getSubject()) && linkFromParser.getPrice().compareTo(linkFromDb.getPrice()) != 0) {
  		switch (alarm.getSubjectWhen()) {
  			case INCREASED: {
  				willBeUpdated = linkFromParser.getPrice().compareTo(linkFromDb.getPrice()) > 0;
  				break;
  			}
  			case DECREASED: {
  				willBeUpdated = linkFromParser.getPrice().compareTo(linkFromDb.getPrice()) < 0;
  				break;
  			}
  			case OUT_OF_LIMITS: {
  				willBeUpdated = checkIfPriceIsOutOfLimits(linkFromParser.getPrice(), alarm.getAmountLowerLimit(), alarm.getAmountUpperLimit());
  				break;
  			}
  			default: {
  				willBeUpdated = true;
  				break;
  			}
  		}
  	}

  	if (willBeUpdated) {
  		String tobeAlarmedPart = "tobe_alarmed=true, ";

    	//checks if it is already notified within 10 mins. if so, no need to disturb the user frequently!
    	if (willBeUpdated && linkFromDb.getAlarmedAt() != null) {
        long diff = DateUtils.findMinuteDiff(linkFromDb.getAlarmedAt(), new Date());
        if (diff <= 10) {
        	tobeAlarmedPart = "";
        }
    	}

  		return "update link set "+tobeAlarmedPart+" updated_at=now() where id=" + linkFromDb.getId();
  	}

  	return null;
  }

  private static boolean checkIfPriceIsOutOfLimits(BigDecimal price, BigDecimal lowerLimit, BigDecimal upperLimit) {
  	if (price.compareTo(BigDecimal.ZERO) > 0) {
  		if (lowerLimit.compareTo(BigDecimal.ZERO) > 0) {
  			boolean yes = price.compareTo(lowerLimit) < 0;
  			if (yes) return true;
  		}
    	if (upperLimit.compareTo(BigDecimal.ZERO) > 0) {
  			boolean yes = price.compareTo(upperLimit) > 0;
  			if (yes) return true;
  		}
  	}
		return false;
  }
	
}
