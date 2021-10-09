package io.inprice.common.meta;

/**
 * Refers to email template files under resource/templates-inlined folder.
 * 
 * @since 2020-12-06
 * @author mdpinar
 */
public enum EmailTemplate {
  
  FORGOT_PASSWORD("forgot-password"),
  REGISTRATION_REQUEST("registration-request"),
  REGISTRATION_COMPLETE("registration-complete"),

  INVITATION_FOR_NEW_USERS("invitation-for-new-users"),
  INVITATION_FOR_EXISTING_USERS("invitation-for-existing-users"),

  PAYMENT_FAILED_FIRST_TIME("payment-failed-first-time"),
  PAYMENT_FAILED_HAS_MORE_DAYS("payment-failed-has-more-days"),
  PAYMENT_FAILED_LAST_TIME("payment-failed-last-time"),

  SUBSCRIPTION_STARTED("subscription-started"),
  SUBSCRIPTION_RENEWAL("subscription-renewal"),
  SUBSCRIPTION_CANCELLED("subscription-cancelled"),
  SUBSCRIPTION_CANCELLED_VOUCHERED("subscription-cancelled-vouchered"),
  SUBSCRIPTION_STOPPED("subscription-stopped"),
  SUBSCRIPTION_CHANGE_SUCCESSFUL("subscription-change-successful"),
  SUBSCRIPTION_CHANGE_SUCCESSFUL_VOUCHERED("subscription-change-successful-vouchered"),
  SUBSCRIPTION_CHANGE_FAILED("subscription-change-failed"),

  FREE_WORKSPACE_REMINDER("free-workspace-reminder"),
  FREE_WORKSPACE_CANCELLED("free-workspace-cancelled"),
  FREE_WORKSPACE_STOPPED("free-workspace-stopped"),

  ALARM_NOTIFICATION("alarm/body")
  
  ;

  private String fileName;

  private EmailTemplate(String fileName) {
    this.fileName = fileName + ".html";
  }

  public String getFileName() {
    return fileName;
  }

}
