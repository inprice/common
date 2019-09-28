package io.inprice.scrapper.common.models;

import java.util.Date;

public class Workspace extends InfoModel {

    private Date dueDate;
    private Date lastCollectingTime;
    private Boolean lastCollectingStatus;
    private Integer retry;
    private Long planId;
    private Long companyId;
    private Date createdAt;

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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + getId() +
                ", active=" + getActive() +
                ", name='" + getName() + '\'' +
                ", dueDate=" + dueDate +
                ", lastCollectingTime=" + lastCollectingTime +
                ", lastCollectingStatus=" + lastCollectingStatus +
                ", retry=" + retry +
                ", planId=" + planId +
                ", companyId=" + companyId +
                ", createdAt=" + createdAt +
                '}';
    }
}
