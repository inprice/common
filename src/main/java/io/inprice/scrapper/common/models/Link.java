package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.LinkStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Link extends Model {

    private String title;
    private String code;
    private String url;
    private String brand;
    private String seller;
    private String shipment;
    private BigDecimal price = BigDecimal.ZERO;
    private Date lastCheck;
    private Date lastUpdate;
    private Integer cycle;
    private LinkStatus status = LinkStatus.NEW;
    private Integer retry;
    private String note;
    private Integer httpStatus;
    private Long customerId;
    private Long customerPlanId;
    private Long productId;
    private Long siteId;
    private String websiteClassName;

    private List<LinkPrice> priceList;
    private List<LinkSpec> specList;
    private List<LinkHistory> historyList;

    public Link() {
    }

    public Link(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public LinkStatus getStatus() {
        return status;
    }

    public void setStatus(LinkStatus status) {
        this.status = status;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", brand='" + brand + '\'' +
                ", seller='" + seller + '\'' +
                ", shipment='" + shipment + '\'' +
                ", price=" + price +
                ", lastCheck=" + lastCheck +
                ", lastUpdate=" + lastUpdate +
                ", cycle=" + cycle +
                ", status=" + status +
                ", retry=" + retry +
                ", note='" + note + '\'' +
                ", httpStatus=" + httpStatus +
                ", customerId=" + customerId +
                ", customerPlanId=" + customerPlanId +
                ", productId=" + productId +
                ", siteId=" + siteId +
                ", websiteClassName='" + websiteClassName + '\'' +
                '}';
    }
}
