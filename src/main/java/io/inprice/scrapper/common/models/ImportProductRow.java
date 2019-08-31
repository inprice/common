package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.ImportType;
import io.inprice.scrapper.common.meta.Status;

import java.io.Serializable;

public class ImportProductRow extends Model {

    private Long importId;
    private ImportType importType;
    private String data;
    private Status status;
    private String description;
    private Long companyId;
    private Long workspaceId;

    private Object productDTO;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

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

    public Object getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(Object productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "ProductImport{" +
                "importId=" + importId +
                ", importType=" + importType +
                ", data='" + data + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                '}';
    }
}
