package io.inprice.scrapper.common.models;

public class PlanRows extends Model {

    private String description;
    private Integer orderNo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "PlanRows{" +
                "description='" + description + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
