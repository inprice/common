package io.inprice.scrapper.common.models;

public class Company extends Model {

    private String name;
    private String contact;
    private String website;
    private Long countryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", website='" + website + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
