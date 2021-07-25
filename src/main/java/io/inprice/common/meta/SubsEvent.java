package io.inprice.common.meta;

import java.util.HashMap;
import java.util.Map;

public enum SubsEvent {

  FREE_USE_STARTED("Free use started"),
  FREE_USE_STOPPED("Free use stopped"),
  FREE_USE_CANCELLED("Free use cancelled"),

  COUPON_USE_STARTED("Coupon use started"),
  COUPON_USE_STOPPED("Coupon use stopped"),
  COUPON_USE_CANCELLED("Coupon use cancelled"),

  SUBSCRIPTION_STARTED("Subscription started"),
  SUBSCRIPTION_RENEWED("Subscription renewed"),
  SUBSCRIPTION_CHANGED("Subscription changed"),
  SUBSCRIPTION_STOPPED("Subscription stopped"),
  SUBSCRIPTION_CANCELLED("Subscription cancelled"),
  
  GIVEN_COUPON("Given coupon"),

  PAYMENT_FAILED("Payment failed");

  private String event;
  private static Map<String, SubsEvent> lookup;

  private SubsEvent(String event) {
    this.event = event;
  }

  public String getEventDesc() {
    return event;
  }

  public static SubsEvent findByDescription(String desc) {
    if (lookup == null) {
      lookup = new HashMap<>(SubsEvent.values().length);
      for (SubsEvent se : SubsEvent.values()) {
        lookup.put(se.getEventDesc(), se);
      }
    }
    return lookup.get(desc);
  }

}