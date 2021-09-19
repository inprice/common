package io.inprice.common.meta;

import java.util.Arrays;
import java.util.List;

public enum WorkspaceStatus {
  
  CREATED(false, true, true, true, false),
  FREE(true, false, true, true, true),
  COUPONED(true, false, false, true, true),
  SUBSCRIBED(true, false, false, false, true),
  CANCELLED(false, false, true, true, false),  //user decission
  STOPPED(false, false, true, true, false), //system decission
  BANNED(false, false, false, false, false);

  private boolean active;
  private boolean okForFreeUse;
  private boolean okForCoupon;
  private boolean okForSubscription;
  private boolean okForCancel;

  public static final List<WorkspaceStatus> ACTIVE_STATUSES = Arrays.asList( FREE, COUPONED, SUBSCRIBED );

  private WorkspaceStatus(boolean active, boolean okForFreeUse, boolean okForCoupon, boolean okForSubscription, boolean okForCancel) {
    this.active = active;
    this.okForFreeUse = okForFreeUse;
    this.okForCoupon = okForCoupon;
    this.okForSubscription = okForSubscription;
    this.okForCancel = okForCancel;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isOKForFreeUse() {
    return okForFreeUse;
  }

  public boolean isOKForCoupon() {
    return okForCoupon;
  }

  public boolean isOKForSubscription() {
    return okForSubscription;
  }

  public boolean isOKForCancel() {
    return okForCancel;
  }

}
