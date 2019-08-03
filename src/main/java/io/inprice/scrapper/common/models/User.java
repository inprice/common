package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.UserType;

import java.util.Date;

public class User extends Model {

    private Boolean active;
    private UserType userType = UserType.USER;
    private String fullName;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private Long companyId;
    private Date lastLoginAt;
    private Date insertAt;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "active=" + active +
                ", userType=" + userType +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                ", lastLoginAt=" + lastLoginAt +
                ", insertAt=" + insertAt +
                '}';
    }
}
