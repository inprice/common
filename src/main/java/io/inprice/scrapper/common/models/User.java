package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.Role;

import java.util.Date;

public class User extends Model {

    private Boolean active;
    private Role role = Role.reader;
    private String fullName;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private Long companyId;
    private Long workspaceId;
    private Date createdAt;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", active=" + active +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", createdAt=" + createdAt +
                '}';
    }
}
