package io.inprice.common;

import io.inprice.common.config.Plans;
import io.inprice.common.models.Plan;

public class Test {
  
  public static void main(String[] args) {
    Plan[] plans = Plans.getPlans();
    for (Plan plan : plans) {
      System.out.println(plan);
    }
  }

}