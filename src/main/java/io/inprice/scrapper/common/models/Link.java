package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.Status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Link extends InfoModel {

    private String sku;
    private String url;
    private String brand;
    private String seller;
    private String shipment;
    private BigDecimal price = BigDecimal.ZERO;
    private Date lastUpdate;
    private Date lastCheck;
    private Status status = Status.NEW;
    private Status previousStatus = Status.NEW;
    private Integer retry;
    private Integer httpStatus;
    private Long customerId;
    private Long customerPlanId;
    private Long productId;
    private Long siteId;
    private String websiteClassName;

    /**
     * The three list fields below never be saved into database.
     */
    private List<LinkPrice> priceList;
    private List<LinkSpec> specList;
    private List<LinkHistory> historyList;

    /**
     * Never be saved into database. It is just used for price change event
     */
    private BigDecimal productPrice;

    public Link() {
    }

    public Link(String url) {
        this.url = url;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.previousStatus = this.status;
        this.status = status;
    }

    public Status getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(Status previousStatus) {
        this.previousStatus = previousStatus;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerPlanId() {
        return customerPlanId;
    }

    public void setCustomerPlanId(Long customerPlanId) {
        this.customerPlanId = customerPlanId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getWebsiteClassName() {
        return websiteClassName;
    }

    public void setWebsiteClassName(String websiteClassName) {
        this.websiteClassName = websiteClassName;
    }

    public List<LinkPrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<LinkPrice> priceList) {
        this.priceList = priceList;
    }

    public List<LinkSpec> getSpecList() {
        return specList;
    }

    public void setSpecList(List<LinkSpec> specList) {
        this.specList = specList;
    }

    public List<LinkHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<LinkHistory> historyList) {
        this.historyList = historyList;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Link{" +
                "sku='" + sku + '\'' +
                ", name='" + getName() + '\'' +
                ", url='" + url + '\'' +
                ", brand='" + brand + '\'' +
                ", seller='" + seller + '\'' +
                ", shipment='" + shipment + '\'' +
                ", price=" + price +
                ", lastUpdate=" + lastUpdate +
                ", lastCheck=" + lastCheck +
                ", status=" + status +
                ", previousStatus=" + previousStatus +
                ", retry=" + retry +
                ", httpStatus=" + httpStatus +
                ", customerId=" + customerId +
                ", customerPlanId=" + customerPlanId +
                ", productId=" + productId +
                ", siteId=" + siteId +
                ", websiteClassName='" + websiteClassName + '\'' +
                '}';
    }
}
