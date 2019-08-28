package io.inprice.scrapper.common.models;

import java.util.Date;

public class LinkImport extends Model {

    private Long productId;
    private String productName;
    private Integer rowCount;
    private Integer inserted;
    private Integer duplicated;
    private Integer ignored;
    private Long companyId;
    private Long workspaceId;
    private Date insertedAt;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getInserted() {
        return inserted;
    }

    public void setInserted(Integer inserted) {
        this.inserted = inserted;
    }

    public Integer getDuplicated() {
        return duplicated;
    }

    public void setDuplicated(Integer duplicated) {
        this.duplicated = duplicated;
    }

    public Integer getIgnored() {
        return ignored;
    }

    public void setIgnored(Integer ignored) {
        this.ignored = ignored;
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

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Override
    public String toString() {
        return "LinkImport{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", rowCount=" + rowCount +
                ", inserted=" + inserted +
                ", duplicated=" + duplicated +
                ", ignored=" + ignored +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", insertedAt=" + insertedAt +
                '}';
    }
}
