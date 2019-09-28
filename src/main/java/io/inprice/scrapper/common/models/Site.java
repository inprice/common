package io.inprice.scrapper.common.models;

import java.util.Date;

public class Site extends InfoModel {

    private String domain;
    private String className;
    private Long countryId;
    private Date createdAt;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Site{" +
                "active=" + getActive() +
                ", name='" + getName() + '\'' +
                ", domain='" + domain + '\'' +
                ", className='" + className + '\'' +
                ", countryId=" + countryId +
                ", createdAt=" + createdAt +
                '}';
    }
}
