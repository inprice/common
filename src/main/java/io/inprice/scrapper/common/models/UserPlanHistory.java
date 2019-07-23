package io.inprice.scrapper.common.models;

import java.util.Date;

public class UserPlanHistory extends Model {

    private Long userPlanId;
    private Boolean collectingStatus;
    private Date insertAt;

    public UserPlanHistory() {
    }

    public UserPlanHistory(Boolean collectingStatus) {
        this.collectingStatus = collectingStatus;
    }

    public UserPlanHistory(Long userPlanId, Boolean collectingStatus) {
        this.userPlanId = userPlanId;
        this.collectingStatus = collectingStatus;
    }

    public Long getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(Long userPlanId) {
        this.userPlanId = userPlanId;
    }

    public Boolean getCollectingStatus() {
        return collectingStatus;
    }

    public void setCollectingStatus(Boolean collectingStatus) {
        this.collectingStatus = collectingStatus;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }
}
