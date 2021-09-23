package io.inprice.common.meta;

import java.util.Arrays;
import java.util.List;

public enum WorkspaceStatus {
  
  CREATED(false, true, true, true, false),
  FREE(true, false, true, true, true),
  CREDITED(true, false, false, true, true),
  SUBSCRIBED(true, false, false, false, true),
  CANCELLED(false, false, true, true, false),  //user decission
  STOPPED(false, false, true, true, false), //system decission
  BANNED(false, false, false, false, false);

  private boolean active;
  private boolean okForFreeUse;
  private boolean okForCredit;
  private boolean okForSubscription;
  private boolean okForCancel;

  public static final List<WorkspaceStatus> ACTIVE_STATUSES = Arrays.asList( FREE, CREDITED, SUBSCRIBED );

  private WorkspaceStatus(boolean active, boolean okForFreeUse, boolean okForCredit, boolean okForSubscription, boolean okForCancel) {
    this.active = active;
    this.okForFreeUse = okForFreeUse;
    this.okForCredit = okForCredit;
    this.okForSubscription = okForSubscription;
    this.okForCancel = okForCancel;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isOKForFreeUse() {
    return okForFreeUse;
  }

  public boolean isOKForCredit() {
    return okForCredit;
  }

  public boolean isOKForSubscription() {
    return okForSubscription;
  }

  public boolean isOKForCancel() {
    return okForCancel;
  }

}
