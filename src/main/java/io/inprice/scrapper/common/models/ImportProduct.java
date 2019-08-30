package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.meta.ImportType;

import java.util.Date;

public class ImportProduct extends Model {

    private ImportType importType;
    private int status;
    private String result;
    private int totalCount;
    private int insertCount;
    private int duplicateCount;
    private int problemCount;
    private Long companyId;
    private Long workspaceId;
    private Date insertedAt;

    public ImportType getImportType() {
        return importType;
    }

    public void setImportType(ImportType importType) {
        this.importType = importType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(int duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    public int getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(int problemCount) {
        this.problemCount = problemCount;
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

    public void incTotalCount() {
        this.totalCount++;
    }

    public void incInsertCount() {
        this.insertCount++;
    }

    public void incDuplicateCount() {
        this.duplicateCount++;
    }

    public void incProblemCount() {
        this.problemCount++;
    }

    @Override
    public String toString() {
        return "ImportProduct{" +
                "importType=" + importType +
                ", status=" + status +
                ", result='" + result + '\'' +
                ", totalCount=" + totalCount +
                ", insertCount=" + insertCount +
                ", duplicateCount=" + duplicateCount +
                ", problemCount=" + problemCount +
                ", companyId=" + companyId +
                ", workspaceId=" + workspaceId +
                ", insertedAt=" + insertedAt +
                '}';
    }
}
