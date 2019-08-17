package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.models.Link;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Info model for notification of deleted links or links whose prices changed
 *
 * @author mdpinar
 */
public class PriceUpdateInfo implements Serializable {

    //if true it means the links is deleted, otherwise, price of the link is updated
    private boolean isDeleted;
    private Long linkId;
    private Long companyId;
    private Long workspaceId;
    private Long productId;
    private BigDecimal newPrice;

    public PriceUpdateInfo(Link link) {
        this(false, link.getId(), link.getCompanyId(), link.getWorkspaceId(), link.getProductId());
        this.newPrice = link.getPrice();
    }

    public PriceUpdateInfo(boolean isDeleted, Long linkId, Long companyId, Long workspaceId, Long productId) {
        this.isDeleted = isDeleted;
        this.linkId = linkId;
        this.companyId = companyId;
        this.workspaceId = workspaceId;
        this.productId = productId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "PriceUpdateInfo{" +
                "isDeleted=" + isDeleted +
                ", linkId=" + linkId +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", productId=" + productId +
                ", newPrice=" + newPrice +
                '}';
    }
}
