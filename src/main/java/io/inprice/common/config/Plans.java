package io.inprice.common.config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.inprice.common.models.Plan;

public class Plans {

  private static final Plan[] plans;
  private static final Map<Integer, Plan> idMap;
  private static final Map<String, Plan> nameMap;

  public static Plan findById(Integer planId) {
    return idMap.get(planId);
  }

  public static Plan findByName(String name) {
    return nameMap.get(name);
  }

  public static Plan[] getPlans() {
    return plans;
  }

  //private static final String RENEWAL_MESSAGE = "Subscription renews every 30 days.";
  private static final String PRODUCT_LIMIT_TEMPLATE = "Up to %d products with unlimited competitors.";

  static {
    plans = new Plan[] {
      new Plan(1,
        "Basic Plan",
        50,
        new BigDecimal(30),
        Arrays.asList(
          String.format(PRODUCT_LIMIT_TEMPLATE, 50)
        ),
        SysProps.PLAN_PAYMENT_ID_BASIC()
      ),
      new Plan(2,
        "Standard Plan",
        100,
        new BigDecimal(50),
        Arrays.asList(
          String.format(PRODUCT_LIMIT_TEMPLATE, 100)
        ),
        SysProps.PLAN_PAYMENT_ID_STANDARD()
      ),
      new Plan(3,
        "Pro Plan",
        250,
        new BigDecimal(80),
        Arrays.asList(
          String.format(PRODUCT_LIMIT_TEMPLATE, 250)
        ),
        SysProps.PLAN_PAYMENT_ID_PRO()
      ),
      new Plan(4,
        "Premium Plan",
        500,
        new BigDecimal(110),
        Arrays.asList(
          String.format(PRODUCT_LIMIT_TEMPLATE, 500)
        ),
        SysProps.PLAN_PAYMENT_ID_PREMIUM()
      )
    };

    idMap = new HashMap<>(plans.length);
    nameMap = new HashMap<>(plans.length);
    for (Plan p: plans) {
      idMap.put(p.getId(), p);
      nameMap.put(p.getName(), p);
    }
  }

}
