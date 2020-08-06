package io.inprice.common.meta;

import java.util.HashMap;
import java.util.Map;

public enum SubsEvent {

  SUBSCRIPTION_STARTED("subscription.start"),
  SUBSCRIPTION_RENEWAL("subscription.renewal"),
  SUBSCRIPTION_CANCELLED("subscription.cancel"),
  COUPON_USED("coupon.used"),
  PAYMENT_FAILED("payment.failed");

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