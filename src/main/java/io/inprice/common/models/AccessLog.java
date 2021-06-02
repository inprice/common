package io.inprice.common.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessLog extends BaseModel {

  private static final long serialVersionUID = 1753526228909974777L;

  private Long userId;
  private String userEmail;
  private String userRole;
  private String accountName;
  private String path;
  private String pathExt;
  private String method;
  private String reqBody;
  private Integer status = 200;
  private String resBody;
  private Integer elapsed; //in milliseconds
  private Boolean slow = Boolean.FALSE;
  private String ip;
  private String agent;

}
