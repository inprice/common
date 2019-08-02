package io.inprice.scrapper.common.models;

import java.math.BigDecimal;
import java.util.Date;

public class LinkPrice extends Model {

    private Long linkId;
    private BigDecimal price;
    private Long companyId;
    private Long workspaceId;
    private Date insertAt;

    public LinkPrice() {
    }

    public LinkPrice(Long linkId, BigDecimal price) {
        this.linkId = linkId;
        this.price = price;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
