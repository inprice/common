package io.inprice.scrapper.common.info;

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
    private Long productId;
    private BigDecimal newPrice;

    public PriceUpdateInfo(Long linkId, Long productId, BigDecimal newPrice) {
        this(false, linkId, productId);
        this.newPrice = newPrice;
    }

    public PriceUpdateInfo(boolean isDeleted, Long linkId, Long productId) {
        this.isDeleted = isDeleted;
        this.linkId = linkId;
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
                ", productId=" + productId +
                ", newPrice=" + newPrice +
                '}';
    }
}
