package io.inprice.common.meta;

import java.util.HashMap;
import java.util.Map;

public enum SubsEvent {

  FREE_USE("Free use started"),
  SUBSCRIPTION_STARTED("Subscription started"),
  SUBSCRIPTION_RENEWAL("Subscription renewed"),
  SUBSCRIPTION_CANCELLED("Subscription cancelled"),
  COUPON_USED("Coupon used"),
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