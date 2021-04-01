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
  AVAILABLE(LinkStatusGroup.ACTIVE),

  
  /*------------------------------------------------------------------------
    WAITING GROUP
   ------------------------------------------------------------------------*/

  /**
   * The initial state.
   */
  TOBE_CLASSIFIED(LinkStatusGroup.WAITING),

  /**
   * Used for the links after TOBE_IMPLEMENTED and INTERNAL_ERROR status
   */
  RESOLVED(LinkStatusGroup.WAITING),

  /**
   * Used for links paused by users.
   * Data will be collected again after RESUMED by the user him/her self
   */
  PAUSED(LinkStatusGroup.WAITING),

  /**
   * Used for paused links to resume.
   */
  RESUMED(LinkStatusGroup.WAITING),

  /**
   * For the links whose websites have not been implemented yet. 
   * Data will be collected after implemented by us
   */
  TOBE_IMPLEMENTED(LinkStatusGroup.WAITING),


  /*------------------------------------------------------------------------
    TRYING GROUP
   ------------------------------------------------------------------------*/

  /**
   * For the links have no sufficient stock
   */
  NOT_AVAILABLE(LinkStatusGroup.TRYING),
  
  /**
   * Used for links returning http error codes greater than 399.
   */
  NETWORK_ERROR(LinkStatusGroup.TRYING),

  /**
   * Used for links blocked by the website's protection system.
   */
  BLOCKED(LinkStatusGroup.TRYING),

  TIMED_OUT(LinkStatusGroup.TRYING),

  
  /*------------------------------------------------------------------------
   PROBLEM GROUP
   ------------------------------------------------------------------------*/
  
  /**
   * Used for indicating links whose data is missing
   */
  NO_DATA(LinkStatusGroup.PROBLEM),

  /**
   * Used for missing links
   */
  NOT_FOUND(LinkStatusGroup.PROBLEM),

  /**
   * Used for internal error like 500.
   * Data will be collected after implemented by us
   */
  INTERNAL_ERROR(LinkStatusGroup.PROBLEM),

  /**
   * Links that will never be implemented
   */
  IMPROPER(LinkStatusGroup.PROBLEM),

  /**
   * Used for indicating links whose data is invalid
   */
  INVALID_DATA(LinkStatusGroup.PROBLEM),

  /**
   * Duplicate links
   */
  DUPLICATE(LinkStatusGroup.PROBLEM);

  private LinkStatusGroup group;

  LinkStatus(LinkStatusGroup group) {
    this.group = group;
  }

  public LinkStatusGroup getGroup() {
    return group;
  }

}
