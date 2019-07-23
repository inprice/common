package io.inprice.scrapper.common.models;

import java.util.Date;

public class UserPlan extends InfoModel {

    private Boolean monthly;
    private Date dueDate;
    private Date lastCollectingTime;
    private Boolean lastCollectingStatus;
    private Integer retry;
    private Long userId;
    private Long brandId;
    private Long planId;
    private Date insertAt;

    public Boolean getMonthly() {
        return monthly;
    }

    public void setMonthly(Boolean monthly) {
        this.monthly = monthly;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getLastCollectingTime() {
        return lastCollectingTime;
    }

    public void setLastCollectingTime(Date lastCollectingTime) {
        this.lastCollectingTime = lastCollectingTime;
    }

    public Boolean getLastCollectingStatus() {
        return lastCollectingStatus;
    }

    public void setLastCollectingStatus(Boolean lastCollectingStatus) {
        this.lastCollectingStatus = lastCollectingStatus;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "active=" + getName() +
                ", name='" + getName() + '\'' +
                ", monthly=" + monthly +
                ", dueDate=" + dueDate +
                ", lastCollectingTime=" + lastCollectingTime +
                ", lastCollectingStatus=" + lastCollectingStatus +
                ", retry=" + retry +
                ", userId=" + userId +
                ", brandId=" + brandId +
                ", planId=" + planId +
                ", insertAt=" + insertAt +
                '}';
    }
}
