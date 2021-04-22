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

  
  /*------------------------------------------------------------------------
    WAITING GROUP
   ------------------------------------------------------------------------*/

  /**
   * The initial state.
   */
  TOBE_CLASSIFIED(LinkStatusGroup.WAITING, "Added."),

  /**
   * Used for the links after TOBE_IMPLEMENTED and INTERNAL_ERROR status
   */
  RESOLVED(LinkStatusGroup.WAITING, "Resolved and be updated."),

  /**
   * Used for links paused by users.
   * Data will be collected again after RESUMED by the user him/her self
   */
  PAUSED(LinkStatusGroup.WAITING, "Manually paused."),

  /**
   * Used for paused links to resume.
   */
  RESUMED(LinkStatusGroup.WAITING, "Activated again and be updated."),

  /**
   * For the links whose websites have not been implemented yet. 
   * Data will be collected after implemented by us
   */
  TOBE_IMPLEMENTED(LinkStatusGroup.WAITING, "New platform, we work on this."),


  /*------------------------------------------------------------------------
    TRYING GROUP
   ------------------------------------------------------------------------*/

  /**
   * For the links have no sufficient stock
   */
  NOT_AVAILABLE(LinkStatusGroup.TRYING, "Insufficient stock number."),
  
  /**
   * Used for links returning http error codes greater than 399.
   */
  NETWORK_ERROR(LinkStatusGroup.TRYING, "Network problem."),

  /**
   * Used for links blocked by the website's protection system.
   */
  BLOCKED(LinkStatusGroup.TRYING, "Blocked by the platform."),

  TIMED_OUT(LinkStatusGroup.TRYING, "Access problem."),

  
  /*------------------------------------------------------------------------
   PROBLEM GROUP
   ------------------------------------------------------------------------*/
  
  /**
   * Used for indicating links whose data is missing
   */
  NO_DATA(LinkStatusGroup.PROBLEM, "Has no price or name."),

  /**
   * Used for missing links
   */
  NOT_FOUND(LinkStatusGroup.PROBLEM, "Page not found."),

  /**
   * Used for internal error like 500.
   * Data will be collected after implemented by us
   */
  INTERNAL_ERROR(LinkStatusGroup.PROBLEM, "Facing a technical problem, we are working on this.");

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
