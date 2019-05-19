package io.inprice.scrapper.common.info;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Info model class for price change notifications for Links
 *
 * @author mdpinar
 */
public class PriceChange implements Serializable {

    private Long linkId;
    private Long productId;
    private BigDecimal newPrice;
    private boolean linkOnly;

    public PriceChange(Long linkId, Long productId, BigDecimal newPrice) {
        this.linkId = linkId;
        this.productId = productId;
        this.newPrice = newPrice;
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

    public boolean isLinkOnly() {
        return linkOnly;
    }

    public void setLinkOnly(boolean linkOnly) {
        this.linkOnly = linkOnly;
    }

    @Override
    public String toString() {
        return "linkId=" + linkId +
                ", productId=" + productId +
                ", newPrice=" + newPrice +
                ", linkOnly=" + linkOnly;
    }
}
