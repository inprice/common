package io.inprice.scrapper.common.models;

import io.inprice.scrapper.common.utils.StringUtils;

public class LinkSpec extends Model {

    private Long linkId;
    private String key;
    private String value;
    private Long companyId;
    private Long workspaceId;

    public LinkSpec() {
    }

    public LinkSpec(String key, String value) {
        this(null, key, value);
    }

    public LinkSpec(Long linkId, String key, String value) {
        this.linkId = linkId;
        this.key = StringUtils.fixQuotes(key.trim());
        this.value = StringUtils.fixQuotes(value.trim());
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringUtils.fixQuotes(key.trim());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = StringUtils.fixQuotes(value.trim());
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
}
