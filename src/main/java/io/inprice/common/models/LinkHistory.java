package io.inprice.common.models;

import io.inprice.common.framework.Exclude;

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
  @Exclude
  private LinkStatus status;
  private Integer httpStatus;
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
