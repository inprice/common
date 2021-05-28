package io.inprice.common.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBanned extends BaseModel {

  private static final long serialVersionUID = -2637012763790476362L;

  private String email;
  private String reason;
  private Boolean voided = Boolean.FALSE; //for allowing the same user to be able to use the platform again!

}
