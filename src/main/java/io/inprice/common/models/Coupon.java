package io.inprice.common.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Coupon extends BaseModel {

  private static final long serialVersionUID = 5125346359199270016L;

  private String code;
  private Integer planId;
  private Integer days;
  private String description;
  private Long issuerId;
  private Long issuedId;
  private Date issuedAt;

  @Override
  public final Long getId() {
  	return null;
  }
  
  @Override
  public void setId(Long id) {
  	;
  }

  @Override
  public final Long getWorkspaceId() {
  	return null;
  }
  
  @Override
  public void setWorkspaceId(Long workspaceId) {
  	;
  }

}
