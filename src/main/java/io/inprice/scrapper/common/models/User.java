package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.UserType;

import java.util.Date;

public class User extends InfoModel {

    private UserType userType = UserType.USER;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private Long companyId;
    private Date insertAt;

    /**
     * The field below never be saved into database. It is used just during validating new users
     */
    private String password;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userType=" + userType +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", companyId=" + companyId +
                ", insertAt=" + insertAt +
                '}';
    }
}
