package io.inprice.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.LinkStatusGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LinkHistory extends BaseModel {

  private static final long serialVersionUID = 885057701505049080L;

  private Long linkId;
  @JsonIgnore
  private LinkStatus status;
  @JsonIgnore
  private String parseCode;
  private Long groupId;

  public LinkHistory(LinkStatus status) {
    this.status = status;
  }

  public LinkHistory(Long linkId, LinkStatus status) {
    this.linkId = linkId;
    this.status = status;
  }

  public LinkStatusGroup getStatusGroup() {
  	return status.getGroup();
  }
  
  public String getStatusDescription() {
  	return status.getDescription();
  }

}
