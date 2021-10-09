package io.inprice.common.models;

import io.inprice.common.meta.WorkspaceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceHistory extends BaseModel {

  private static final long serialVersionUID = -323250016123276994L;

  private WorkspaceStatus status;
  private Integer planId;
  
  //transients
  private String planName;

}
