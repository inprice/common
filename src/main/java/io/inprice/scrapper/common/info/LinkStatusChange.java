package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.meta.LinkStatus;
import io.inprice.scrapper.common.models.Link;

import java.io.Serializable;

public class LinkStatusChange implements Serializable {

    private Long linkId;
    private LinkStatus oldStatus;
    private LinkStatus newStatus;
    private int httpStatus;
    private String note;

    public LinkStatusChange(Link link, LinkStatus newStatus) {
        this.linkId = link.getId();
        this.oldStatus = link.getStatus();
        this.newStatus = newStatus;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public LinkStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(LinkStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public LinkStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(LinkStatus newStatus) {
        this.newStatus = newStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "linkId=" + linkId +
                ", oldStatus=" + oldStatus +
                ", newStatus=" + newStatus +
                ", httpStatus=" + httpStatus +
                ", note=" + note ;
    }
}
