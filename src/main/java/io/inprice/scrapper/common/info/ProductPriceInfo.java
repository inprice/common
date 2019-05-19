package io.inprice.scrapper.common.info;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Info model class for price change notifications for Products
 *
 * @author mdpinar
 */
public class ProductPriceInfo implements Serializable {

    private Long productId;
    private BigDecimal price;

    public ProductPriceInfo(Long productId) {
        this(productId, null);
    }

    public ProductPriceInfo(Long productId, BigDecimal price) {
        this.productId = productId;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceInfo that = (ProductPriceInfo) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "ProductPriceInfo{" +
                "productId=" + productId +
                ", price=" + price +
                '}';
    }
}
