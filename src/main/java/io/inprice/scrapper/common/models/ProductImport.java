package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.FileType;

import java.util.Date;

public class ProductImport extends Model {

    private FileType fileType;
    private Integer rowCount;
    private Integer inserted;
    private Integer duplicated;
    private Integer ignored;
    private Long companyId;
    private Long workspaceId;
    private Date insertedAt;

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
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
        return "ProductImport{" +
                ", fileType=" + fileType +
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
