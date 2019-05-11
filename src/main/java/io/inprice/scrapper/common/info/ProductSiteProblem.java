package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.meta.LinkStatus;

import java.io.Serializable;

public class ProductSiteProblem implements Serializable {

    private Long siteId;
    private LinkStatus status;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public LinkStatus getStatus() {
        return status;
    }

    public void setStatus(LinkStatus status) {
        this.status = status;
    }
}
