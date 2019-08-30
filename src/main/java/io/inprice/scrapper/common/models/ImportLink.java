package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.ImportType;
import io.inprice.scrapper.common.meta.Status;

import java.util.Date;

public class ImportLink extends Model {

    private ImportType importType;
    private String data;
    private Status status;
    private String description;
    private Long productId;
    private Long companyId;
    private Long workspaceId;
    private Date insertedAt;

    public ImportType getImportType() {
        return importType;
    }

    public void setImportType(ImportType importType) {
        this.importType = importType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        return "ProductImport{" +
                "importType=" + importType +
                ", data='" + data + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", insertedAt=" + insertedAt +
                '}';
    }
}
