package io.inprice.scrapper.common.models;

public class InfoModel extends Model {

    private Boolean active = Boolean.TRUE;
    private String name;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
