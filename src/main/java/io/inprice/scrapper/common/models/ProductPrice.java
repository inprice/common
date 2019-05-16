package io.inprice.scrapper.common.models;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPrice extends Model {

    private Long productId;
    private String  minSeller;
    private String  maxSeller;
    private BigDecimal price;
    private BigDecimal minPrice;
    private BigDecimal avgPrice;
    private BigDecimal maxPrice;
    private Date insertAt;

    public ProductPrice() {
    }

    public ProductPrice(Long productId, String minSeller, String maxSeller, BigDecimal price, BigDecimal minPrice, BigDecimal avgPrice, BigDecimal maxPrice) {
        this.productId = productId;
        this.minSeller = minSeller;
        this.maxSeller = maxSeller;
        this.price = price;
        this.minPrice = minPrice;
        this.avgPrice = avgPrice;
        this.maxPrice = maxPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMinSeller() {
        return minSeller;
    }

    public void setMinSeller(String minSeller) {
        this.minSeller = minSeller;
    }

    public String getMaxSeller() {
        return maxSeller;
    }

    public void setMaxSeller(String maxSeller) {
        this.maxSeller = maxSeller;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }
}
