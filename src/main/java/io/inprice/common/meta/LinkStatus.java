package io.inprice.common.meta;

import java.util.Arrays;
import java.util.List;

/**
 * This class consists of several statuses that are used to manage links's
 * positions.
 *
 */
public enum LinkStatus {

  /*------------------------------------------------------------------------
    ACTIVE GROUP
    In this group, links are periodically fetched from db 
   ------------------------------------------------------------------------*/

  /**
   * The initial state.
   */
  TOBE_CLASSIFIED(LinkStatus.ACTIVE_GROUP),

  /**
   * Everything is ok.
   */
  AVAILABLE(LinkStatus.ACTIVE_GROUP),

  /**
   * Used for the links after TOBE_IMPLEMENTED and INTERNAL_ERROR status
   */
  RESOLVED(LinkStatus.ACTIVE_GROUP),


  /*------------------------------------------------------------------------
    FAILED GROUP
    Links are periodically fetched up to a certain times (ie three times)
   ------------------------------------------------------------------------*/

  /**
   * For the links have no sufficient stock
   */
  NOT_AVAILABLE(LinkStatus.FAILED_GROUP),

  /**
   * Used for indicating links whose data is missing
   */
  NO_DATA(LinkStatus.FAILED_GROUP),

  /**
   * Used for links returning http error codes greater than 399.
   */
  NETWORK_ERROR(LinkStatus.FAILED_GROUP),


  /*------------------------------------------------------------------------
   PASSIVE GROUP
   Links in this group will never be handled automatically
   ------------------------------------------------------------------------*/

  /**
   * Used for paused links to resume.
   */
  RESUMED(LinkStatus.PASSIVE_GROUP),

  /**
   * For the links whose websites have not been implemented yet. 
   * Data will be collected after implemented by us
   */
  TOBE_IMPLEMENTED(LinkStatus.PASSIVE_GROUP),

  /**
   * Used for internal error like 500.
   * Data will be collected after implemented by us
   */
  INTERNAL_ERROR(LinkStatus.PASSIVE_GROUP),

  /**
   * Used for links paused by users.
   * Data will be collected again after RESUMED by the user him/her self
   */
  PAUSED(LinkStatus.PASSIVE_GROUP),

  /**
   * Links that will never be implemented
   */
  IMPROPER(LinkStatus.PASSIVE_GROUP),

  /**
   * Used for indicating links whose data is invalid
   */
  INVALID_DATA(LinkStatus.PASSIVE_GROUP),

  /**
   * Duplicate links
   */
  DUPLICATE(LinkStatus.PASSIVE_GROUP),

  /**
   * For imported products
   */
  IMPORTED(LinkStatus.PASSIVE_GROUP),

  /**
   * For imported products
   */
  LIMIT_EXCEEDED(LinkStatus.PASSIVE_GROUP);

  private String group;

  //Continuously checks the data
  public static final String ACTIVE_GROUP = "active";

  //Checks the data up to a certain number
  public static final String FAILED_GROUP = "failed";

  //For the linls in this group, data never be collected.
  public static final String PASSIVE_GROUP = "passive";

  public static final List<LinkStatus> ACTIVE_STATUSES = Arrays.asList( TOBE_CLASSIFIED, AVAILABLE, RESOLVED );
  public static final List<LinkStatus> FAILED_STATUSES = Arrays.asList( NOT_AVAILABLE, NETWORK_ERROR );

  LinkStatus(String group) {
    this.group = group;
  }

  public String getGroup() {
    return group;
  }

}
