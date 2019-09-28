package io.inprice.scrapper.common.models;

import java.util.Date;

public class WorkspaceHistory extends Model {

    private Boolean collectingStatus;
    private Long companyId;
    private Long workspaceId;
    private Date createdAt;

    public Boolean getCollectingStatus() {
        return collectingStatus;
    }

    public void setCollectingStatus(Boolean collectingStatus) {
        this.collectingStatus = collectingStatus;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
