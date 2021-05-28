package io.inprice.common.models;

import io.inprice.common.meta.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountHistory extends BaseModel {

  private static final long serialVersionUID = -323250016123276994L;

  private AccountStatus status;
  private Integer planId;
  
  //transients
  private String planName;

}
