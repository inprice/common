package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.Status;

import java.util.Date;

public class LinkHistory extends Model {

    private Long linkId;
    private Status status = Status.NEW;
    private Integer httpStatus;
    private Long companyId;
    private Long workspaceId;
    private Date insertAt;

    public LinkHistory() {
    }

    public LinkHistory(Status status) {
        this.status = status;
    }

    public LinkHistory(Long linkId, Status status) {
        this.linkId = linkId;
        this.status = status;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
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

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }
}
