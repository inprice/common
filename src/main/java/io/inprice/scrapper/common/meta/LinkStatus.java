package io.inprice.scrapper.common.meta;

import java.util.HashSet;
import java.util.Set;

public enum LinkStatus {

    NEW("neutral"),
    ACTIVE("positive"),
    BE_IMPLEMENTED("neutral"),
    IMPROPER("negative"),
    UNAVAILABLE("negative"),
    SOCKET_ERROR("positive"),
    NETWORK_ERROR("negative"),
    CLASS_PROBLEM("positive"),
    INTERNAL_ERROR("positive"),
    PAUSED("user"),
    REPORTED("user"),
    UNKNOWN("negative");

    private String group;

    LinkStatus(String group) {
        this.group = group;
    }

    private static final Set<LinkStatus> positiveSet = new HashSet<>();
    private static final Set<LinkStatus> negativeSet = new HashSet<>();
    private static final Set<LinkStatus> userDrivenSet = new HashSet<>();
    private static final Set<LinkStatus> neutralSet = new HashSet<>();

    private static StringBuilder joinedPositives = new StringBuilder();
    private static StringBuilder joinedNegatives = new StringBuilder();
    private static StringBuilder joinedUserDrivens = new StringBuilder();
    private static StringBuilder joinedNeutrals = new StringBuilder();

    static {
        for (LinkStatus ls: values()) {
            switch (ls.group) {
                case "positive": {
                    positiveSet.add(ls);
                    if (joinedPositives.length() > 0) joinedPositives.append(",");
                    joinedPositives.append("'");
                    joinedPositives.append(ls.name());
                    joinedPositives.append("'");
                    break;
                }
                case "negative": {
                    negativeSet.add(ls);
                    if (joinedNegatives.length() > 0) joinedNegatives.append(",");
                    joinedNegatives.append("'");
                    joinedNegatives.append(ls.name());
                    joinedNegatives.append("'");
                    break;
                }
                case "user": {
                    userDrivenSet.add(ls);
                    if (joinedUserDrivens.length() > 0) joinedUserDrivens.append(",");
                    joinedUserDrivens.append("'");
                    joinedUserDrivens.append(ls.name());
                    joinedUserDrivens.append("'");
                    break;
                }
                case "neutral": {
                    neutralSet.add(ls);
                    if (joinedNeutrals.length() > 0) joinedNeutrals.append(",");
                    joinedNeutrals.append("'");
                    joinedNeutrals.append(ls.name());
                    joinedNeutrals.append("'");
                    break;
                }
            }
        }
    }

    public LinkStatus findByName(String name) {
        try {
            return LinkStatus.valueOf(name);
        } catch (Exception e) {
            return LinkStatus.UNKNOWN;
        }
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

    public Set<LinkStatus> getPositiveSet() {
        return positiveSet;
    }

    public Set<LinkStatus> getNegativeSet() {
        return negativeSet;
    }

    public Set<LinkStatus> getUserDrivenSet() {
        return userDrivenSet;
    }

    public Set<LinkStatus> getNeutralSet() {
        return neutralSet;
    }

}
