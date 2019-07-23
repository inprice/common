package io.inprice.scrapper.common.models;

import java.util.Date;

public class UserBrand extends Model {

    private String name;
    private Long userId;
    private Date insertAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public String toString() {
        return "UserBrand{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", insertAt=" + insertAt +
                '}';
    }
}
