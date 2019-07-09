package io.inprice.scrapper.common.models;

import java.util.Date;

public class Site extends InfoModel {

    private String domain;
    private String className;
    private Long countryId;
    private Date insertAt;

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

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public String toString() {
        return "Site{" +
                "active=" + getActive() +
                ", name='" + getName() + '\'' +
                ", domain='" + domain + '\'' +
                ", className='" + className + '\'' +
                ", countryId=" + countryId +
                ", insertAt=" + insertAt +
                '}';
    }
}
