package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.LinkStatus;

import java.util.Date;

public class LinkHistory extends Model {

    private Long linkId;
    private LinkStatus status = LinkStatus.NEW;
    private Integer httpStatus;
    private Date insertAt;

    public LinkHistory() {
    }

    public LinkHistory(LinkStatus status) {
        this.status = status;
    }

    public LinkHistory(Long linkId, LinkStatus status) {
        this.linkId = linkId;
        this.status = status;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public LinkStatus getStatus() {
        return status;
    }

    public void setStatus(LinkStatus status) {
        this.status = status;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }
}
