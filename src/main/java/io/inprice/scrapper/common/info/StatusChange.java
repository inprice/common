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
    private Status oldStatus;

    public StatusChange() {
    }

    public StatusChange(Link link, Status oldStatus) {
        this.link = link;
        this.oldStatus = oldStatus;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Status getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Status newStatus) {
        this.oldStatus = oldStatus;
    }

}
