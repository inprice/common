package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.Status;

public class LinkImportRow extends Model {

    private String url;
    private Status status;
    private String description;
    private Long productId;
    private Long importId;
    private Long companyId;
    private Long workspaceId;
    private Long linkId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
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

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    @Override
    public String toString() {
        return "LinkImportRows{" +
                "url='" + url + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", importId=" + importId +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", linkId=" + linkId +
                '}';
    }
}
