package io.inprice.scrapper.common.info;

import java.math.BigDecimal;

public class PriceChange {

    private Long linkId;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;

    public PriceChange(Long linkId, BigDecimal newPrice) {
        this.linkId = linkId;
        this.newPrice = newPrice;
    }

    public PriceChange(Long linkId, BigDecimal oldPrice, BigDecimal newPrice) {
        this.linkId = linkId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
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
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice;
    }
}
