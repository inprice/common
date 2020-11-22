package io.inprice.common.meta;

public enum SubsStatus {
  
  NOT_SET(true, true, true, false),
  FREE(false, true, true, true),
  COUPONED(false, false, true, true),
  SUBSCRIBED(false, false, false, true),
  CANCELLED(true, true, true, false),  //user decission
  STOPPED(false, false, false, false); //system decission

  private boolean isOKForFreeUse;
  private boolean isOKForCoupon;
  private boolean isOKForSubscription;
  private boolean isOKForCancel;

  private SubsStatus(boolean isOKForFreeUse, boolean isOKForCoupon, boolean isOKForSubscription, boolean isOKForCancel) {
    this.isOKForFreeUse = isOKForFreeUse;
    this.isOKForCoupon = isOKForCoupon;
    this.isOKForSubscription = isOKForSubscription;
    this.isOKForCancel = isOKForCancel;
  }

  public boolean isOKForFreeUse() {
    return isOKForFreeUse;
  }

  public boolean isOKForCoupon() {
    return isOKForCoupon;
  }

  public boolean isOKForSubscription() {
    return isOKForSubscription;
  }

  public boolean isOKForCancel() {
    return isOKForCancel;
  }

}