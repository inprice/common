package io.inprice.common.meta;

/**
 * This class consists of several statuses that are used to manage links's positions
 *
 */
public enum LinkStatus {

  /*------------------------------------------------------------------------
    ACTIVE PRODUCT
   ------------------------------------------------------------------------*/

  /**
   * Everything is ok.
   */
  AVAILABLE(Grup.ACTIVE, "Everything is fine."),

  
  /*------------------------------------------------------------------------
    WAITING PRODUCT
   ------------------------------------------------------------------------*/

  /**
   * The initial state.
   */
  TOBE_CLASSIFIED(Grup.WAITING, "Added."),

  /**
   * For the links whose websites have not been implemented yet. 
   * Data will be collected after implemented by us
   */
  TOBE_IMPLEMENTED(Grup.WAITING, "New platform, we work on this."),

  /**
   * Used for the links after TOBE_IMPLEMENTED and INTERNAL_ERROR status
   */
  RESOLVED(Grup.WAITING, "Resolved and be updated soon."),

  /**
   * To refresh and fetch the data one more time
   */
  REFRESHED(Grup.WAITING, "Will be refreshed in a short while."),

  /**
   * Used for links paused by super user.
   */
  PAUSED(Grup.WAITING, "Manually paused."),


  /*------------------------------------------------------------------------
    TRYING PRODUCT
   ------------------------------------------------------------------------*/

  /**
   * For the links have no sufficient stock
   */
  NOT_AVAILABLE(Grup.TRYING, "Insufficient stock."),

  /**
   * Used for links returning http error codes greater than 399.
   */
  NETWORK_ERROR(Grup.TRYING, "Network problem."),

  TIMED_OUT(Grup.TRYING, "Temporarily access problem."),

  /**
   * Website seems down
   */
  SITE_DOWN(Grup.TRYING, "Website is down."),

  /**
   * Used for internal error like 500.
   * Data will be collected after implemented by us
   */
  INTERNAL_ERROR(Grup.TRYING, "Having technical problem, we are working on this!"),

  
  /*------------------------------------------------------------------------
   PROBLEM PRODUCT
   ------------------------------------------------------------------------*/
  
  /**
   * Used for indicating links whose data is missing
   */
  NO_DATA(Grup.PROBLEM, "Has no price or name!"),

  /**
   * Used for missing links
   */
  NOT_FOUND(Grup.PROBLEM, "Product not found!"),

  /**
   * Used for not implemented links
   */
  NOT_SUITABLE(Grup.PROBLEM, "This url won't be handled since not proper!"),

  /**
   * Used for blocked websites like walmart.ca
   */
  NOT_ALLOWED(Grup.PROBLEM, "Access problem, unable to read data.");

  private Grup grup;
  private String description;

  LinkStatus(Grup grup, String description) {
    this.grup = grup;
    this.description = description;
  }

  public Grup getGrup() {
    return grup;
  }

  public String getDescription() {
		return description;
	}

}
