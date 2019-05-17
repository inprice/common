package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.meta.LinkStatus;
import io.inprice.scrapper.common.models.Link;

import java.io.Serializable;

public class LinkStatusChange implements Serializable {

    private Link link;
    private LinkStatus newStatus;

    public LinkStatusChange(Link link, LinkStatus newStatus) {
        this.link = link;
        this.newStatus = newStatus;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public LinkStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(LinkStatus newStatus) {
        this.newStatus = newStatus;
    }
}
