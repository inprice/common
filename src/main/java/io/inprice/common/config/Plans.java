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
  private static final Map<String, Plan> priceIdMap;

  private static final String LINK_LIMIT_TEMPLATE = "Up to %d links.";
  private static final String UNLIMITED_COMPETITORS = "Unlimited competitors.";
  private static final String UPDATE_NUMBER = "Update prices 4 times a day.";
  private static final String FREE_API = "Free API access (coming soon).";

  public static Plan findById(Integer planId) {
    return idMap.get(planId);
  }

  public static Plan findByName(String name) {
    return nameMap.get(name);
  }

  public static Plan findByPriceId(String priceId) {
    return priceIdMap.get(priceId);
  }

  public static Plan[] getPlans() {
    return plans;
  }

  static {
    int i = 0;
    plans = new Plan[] {
      new Plan(i++,
        "Basic Plan",
        25,
        new BigDecimal(15),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 25),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER
        ),
        "price_1HsPmfBiHTcqawyM6l9e38eF"
      ),
      new Plan(i++,
        "Starter Plan",
        50,
        new BigDecimal(30),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 50),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER
        ),
        "price_1HsPnMBiHTcqawyMBiFZh437"
      ),
      new Plan(i++,
        "Standard Plan",
        100,
        new BigDecimal(50),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 100),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER
        ),
        "price_1HsPnkBiHTcqawyMWpAcIGiM"
      ),
      new Plan(i++,
        "Pro Plan",
        250,
        new BigDecimal(70),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 250),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER,
          FREE_API
        ),
        "price_1HsPo9BiHTcqawyMkrYojUEh"
      ),
      new Plan(i++,
        "Premium Plan",
        500,
        new BigDecimal(100),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 500),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER,
          FREE_API
        ),
        "price_1HsPoYBiHTcqawyMppp1waDG"
      ),
      new Plan(i++,
        "Enterprise Plan",
        1000,
        new BigDecimal(130),
        Arrays.asList(
          String.format(LINK_LIMIT_TEMPLATE, 1000),
          UNLIMITED_COMPETITORS,
          UPDATE_NUMBER,
          FREE_API
        ),
        "price_1HsPowBiHTcqawyM8e1wer70"
      )
    };

    idMap = new HashMap<>(plans.length);
    nameMap = new HashMap<>(plans.length);
    priceIdMap = new HashMap<>(plans.length);
    for (Plan p: plans) {
      idMap.put(p.getId(), p);
      nameMap.put(p.getName(), p);
      priceIdMap.put(p.getStripePriceId(), p);
    }
  }

}
