package io.inprice.scrapper.common.info;

import java.math.BigDecimal;

public class PriceChange {

    private Long linkId;
    private Long productId;
    private BigDecimal newPrice;

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

    @Override
    public String toString() {
        return "linkId=" + linkId +
                ", productId=" + productId +
                ", newPrice=" + newPrice;
    }
}
