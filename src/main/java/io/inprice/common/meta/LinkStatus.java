package io.inprice.common.meta;

/**
 * This class consists of several statuses that are used to manage links's levels
 *
 */
public enum LinkStatus {

  /*------------------------------------------------------------------------
    ACTIVE GROUP
   ------------------------------------------------------------------------*/

  /**
   * Everything is ok.
   */
  AVAILABLE(LinkStatusGroup.ACTIVE, "Everything is fine."),

  /**
   * Used for the links after TOBE_IMPLEMENTED and INTERNAL_ERROR status
   */
  RESOLVED(LinkStatusGroup.ACTIVE, "Resolved and be updated."),

  /**
   * To refresh and fetch the data one more time
   */
  REFRESHED(LinkStatusGroup.ACTIVE, "Will be refreshed in a short while."),

  
  /*------------------------------------------------------------------------
    WAITING GROUP
   ------------------------------------------------------------------------*/

  /**
   * The initial state.
   */
  TOBE_CLASSIFIED(LinkStatusGroup.WAITING, "Added."),

  /**
   * For the links whose websites have not been implemented yet. 
   * Data will be collected after implemented by us
   */
  TOBE_IMPLEMENTED(LinkStatusGroup.WAITING, "New platform, we work on this."),

  /**
   * Used for links paused by super user.
   */
  PAUSED(LinkStatusGroup.WAITING, "Manually paused."),


  /*------------------------------------------------------------------------
    TRYING GROUP
   ------------------------------------------------------------------------*/

  /**
   * For the links have no sufficient stock
   */
  NOT_AVAILABLE(LinkStatusGroup.TRYING, "Insufficient stock."),

  /**
   * Used for links returning http error codes greater than 399.
   */
  NETWORK_ERROR(LinkStatusGroup.TRYING, "Network problem."),

  TIMED_OUT(LinkStatusGroup.TRYING, "Temporarily access problem."),

  /**
   * Website seems down
   */
  SITE_DOWN(LinkStatusGroup.TRYING, "Website is down."),

  /**
   * Used for internal error like 500.
   * Data will be collected after implemented by us
   */
  INTERNAL_ERROR(LinkStatusGroup.TRYING, "Having technical problem, we are working on this!"),

  
  /*------------------------------------------------------------------------
   PROBLEM GROUP
   ------------------------------------------------------------------------*/
  
  /**
   * Used for indicating links whose data is missing
   */
  NO_DATA(LinkStatusGroup.PROBLEM, "Has no price or name!"),

  /**
   * Used for missing links
   */
  NOT_FOUND(LinkStatusGroup.PROBLEM, "Page not found!"),

  /**
   * Used for not implemented links
   */
  NOT_SUITABLE(LinkStatusGroup.PROBLEM, "This url won't be handled since not proper!"),

  /**
   * Used for blocked websites like walmart.ca
   */
  NOT_ALLOWED(LinkStatusGroup.PROBLEM, "Access problem, unable to read data.");

  private LinkStatusGroup group;
  private String description;

  LinkStatus(LinkStatusGroup group, String description) {
    this.group = group;
    this.description = description;
  }

  public LinkStatusGroup getGroup() {
    return group;
  }

  public String getDescription() {
		return description;
	}

}
