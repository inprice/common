package io.inprice.scrapper.common.models;

import java.math.BigDecimal;

public class Plan extends InfoModel {

    private BigDecimal price;
    private Integer rowLimit;
    private Integer orderNo;

    public Integer getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(Integer rowLimit) {
        this.rowLimit = rowLimit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name=" + getName() +
                "price=" + price +
                ", rowLimit=" + rowLimit +
                ", orderNo=" + orderNo +
                '}';
    }
}
