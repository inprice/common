package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.meta.Status;
import io.inprice.scrapper.common.models.Link;

import java.io.Serializable;

/**
 * Info model class for status change notifications
 *
 * @author mdpinar
 */
public class StatusChange implements Serializable {

    private Link link;
    private Status newStatus;

    public StatusChange(Link link, Status newStatus) {
        this.link = link;
        this.newStatus = newStatus;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

}
