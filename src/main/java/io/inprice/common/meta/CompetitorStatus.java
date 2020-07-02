package io.inprice.common.meta;

import java.util.HashSet;
import java.util.Set;

/**
 * This class consists of several statuses that are used to manage competitors's
 * positions.
 *
 * The statuses below are divided into three groups: a) neutral The competitors
 * in this group are needed to be handled differently. NEW status, for example,
 * is the initial position. This group will be excluded from analysis and
 * reports.
 *
 * b) positive This group is used for AVAILABLE state and internal problems like
 * SOCKET_ERROR and ClASS_PROBLEM. Other than AVAILABLE state, the competitors
 * in this group actually have no problems. So, they are needed to be handled by
 * a developer. This group will be included in analysis and reports.
 *
 * c) negative This group is used for troubled states like UNAVAILABLE and
 * NETWORK_ERROR. The competitors in this group have problems in network or
 * e-commerce website. This group will be excluded from analysis and reports.
 *
 * d) user User driven competitors take place in this group. This group will be
 * excluded from analysis and reports.
 *
 */
public enum CompetitorStatus {

  /**
   * The initial state. Used for first recorded competitors.
   */
  TOBE_CLASSIFIED("neutral"),

  /**
   * Used for competitors which need to be renewed
   */
  TOBE_RENEWED("neutral"),

  /**
   * Used for competitors whose websites have not been implemented yet. Data won't
   * be collected till implemented
   */
  TOBE_IMPLEMENTED("neutral"),

  /**
   * Used for newly implemented competitors
   */
  IMPLEMENTED("neutral"),

  /**
   * Used for competitors which have no problem about collecting data
   */
  AVAILABLE("positive"),

  /**
   * Used for competitors that will never be implemented
   */
  WONT_BE_IMPLEMENTED("negative"),

  /**
   * Used for duplicate competitors/products
   */
  DUPLICATE("negative"),

  /**
   * Used for indicating competitors whose URLs are wrong Data never be collected
   */
  IMPROPER("negative"),

  /**
   * Used for indicating competitors whose URLs not specific The url may point a
   * search page or a product listing page on the website A url must refer only
   * one product's page on which price, name... exist Data never be collected
   */
  NOT_A_PRODUCT_PAGE("negative"),

  /**
   * Used for indicating competitors whose data is missing
   */
  NO_DATA("negative"),

  /**
   * The competitors have no sufficient stock are put in this state Data won't be
   * collected till it is available again. Availability checks are done a certain
   * times (look at system parameters)
   */
  NOT_AVAILABLE("negative"),

  /**
   * Used for indicating competitors whose data has problem
   */
  READ_ERROR("negative"),

  /**
   * Used for specifying competitors whose website block us for data collecting at
   * first step Availability checks are done a certain times (look at system
   * parameters)
   */
  SOCKET_ERROR("neutral"),

  /**
   * Used for competitors returning http error codes greater than 399 Data won't
   * be collected till it is available again. Availability checks are done a
   * certain times (look at system parameters)
   */
  NETWORK_ERROR("negative"),

  /**
   * This state is used for indicating competitors which we face problem during
   * resolving the class name Availability checks are done a certain times (look
   * at system parameters)
   */
  CLASS_PROBLEM("negative"),

  /**
   * Used for internal error like 500 Data won't be collected till it is available
   * again. Availability checks are done a certain times (look at system
   * parameters)
   */
  INTERNAL_ERROR("negative"),

  /**
   * Used for blocked competitors like 503
   */
  BLOCKED("negative"),

  /**
   * Used for competitors paused by users. Data won't be collected till it is
   * activated again.
   */
  PAUSED("user"),

  /**
   * Used for paused competitors to resume.
   */
  RESUMED("user");

  private String group;

  CompetitorStatus(String group) {
    this.group = group;
  }

  /**
   * Set of positive statuses
   */
  private static final Set<CompetitorStatus> positiveSet = new HashSet<>();

  /**
   * Set of negative statuses
   */
  private static final Set<CompetitorStatus> negativeSet = new HashSet<>();

  /**
   * Set of user driven statuses
   */
  private static final Set<CompetitorStatus> userDrivenSet = new HashSet<>();

  /**
   * Set of neutral statuses
   */
  private static final Set<CompetitorStatus> neutralSet = new HashSet<>();

  /**
   * Joined statues of positives separated by comma
   */
  private static StringBuilder joinedPositives = new StringBuilder();

  /**
   * Joined statues of negatives separated by comma
   */
  private static StringBuilder joinedNegatives = new StringBuilder();

  /**
   * Joined statues of user drivens separated by comma
   */
  private static StringBuilder joinedUserDrivens = new StringBuilder();

  /**
   * Joined statues of neutrals separated by comma
   */
  private static StringBuilder joinedNeutrals = new StringBuilder();

  /*
   * Separating all the statuses by groups
   */
  static {
    for (CompetitorStatus ls : values()) {
      switch (ls.group) {
        case "positive": {
          positiveSet.add(ls);
          if (joinedPositives.length() > 0)
            joinedPositives.append(",");
          joinedPositives.append("'");
          joinedPositives.append(ls.name());
          joinedPositives.append("'");
          break;
        }
        case "negative": {
          negativeSet.add(ls);
          if (joinedNegatives.length() > 0)
            joinedNegatives.append(",");
          joinedNegatives.append("'");
          joinedNegatives.append(ls.name());
          joinedNegatives.append("'");
          break;
        }
        case "user": {
          userDrivenSet.add(ls);
          if (joinedUserDrivens.length() > 0)
            joinedUserDrivens.append(",");
          joinedUserDrivens.append("'");
          joinedUserDrivens.append(ls.name());
          joinedUserDrivens.append("'");
          break;
        }
        case "neutral": {
          neutralSet.add(ls);
          if (joinedNeutrals.length() > 0)
            joinedNeutrals.append(",");
          joinedNeutrals.append("'");
          joinedNeutrals.append(ls.name());
          joinedNeutrals.append("'");
          break;
        }
      }
    }
  }

  /**
   * Finds CompetitorStatus by given name
   *
   * @param name
   * @return CompetitorStatus
   */
  public CompetitorStatus findByName(String name) {
    return CompetitorStatus.valueOf(name);
  }

  public String getGroup() {
    return group;
  }

  public boolean isPositive() {
    return "positive".equals(group);
  }

  public boolean isNegative() {
    return "negative".equals(group);
  }

  public boolean isUserDriven() {
    return "user".equals(group);
  }

  public boolean isNeutral() {
    return "neutral".equals(group);
  }

  public static StringBuilder getJoinedPositives() {
    return joinedPositives;
  }

  public static StringBuilder getJoinedNegatives() {
    return joinedNegatives;
  }

  public static StringBuilder getJoinedUserDrivens() {
    return joinedUserDrivens;
  }

  public static StringBuilder getJoinedNeutrals() {
    return joinedNeutrals;
  }

  public Set<CompetitorStatus> getPositiveSet() {
    return positiveSet;
  }

  public Set<CompetitorStatus> getNegativeSet() {
    return negativeSet;
  }

  public Set<CompetitorStatus> getUserDrivenSet() {
    return userDrivenSet;
  }

  public Set<CompetitorStatus> getNeutralSet() {
    return neutralSet;
  }

}
