package io.inprice.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
  
	public static void main(String[] args) {
		BigDecimal A_HUNDRED = new BigDecimal(100);

  	BigDecimal diffAmount = BigDecimal.ZERO;
  	BigDecimal diffRate = BigDecimal.ZERO;

		BigDecimal oldPrice = BigDecimal.valueOf(7264);
		BigDecimal newPrice = BigDecimal.valueOf(7234);
		
  	if (oldPrice.compareTo(BigDecimal.ZERO) != 0) {
    	diffAmount = newPrice.subtract(oldPrice).setScale(2, RoundingMode.HALF_UP);
    	if (diffAmount.compareTo(BigDecimal.ZERO) != 0) {
    		diffRate = diffAmount.divide(oldPrice, 6, RoundingMode.HALF_UP).multiply(A_HUNDRED).setScale(2, RoundingMode.HALF_UP);
    	}
  	}
  	
  	System.out.println("diffAmount --> " + diffAmount);
  	System.out.println("diffRate   --> " + diffRate);
  }

}