package io.inprice.common.models.analytics;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessLog implements Serializable {

  private static final long serialVersionUID = 1753526228909974777L;

  private Long id;
  private Long userId;
  private String userEmail;
  private String userRole;
  private String accountName;
  private String ip;
  private String path;
  private String pathExt;
  private String method;
  private String reqBody;
  private Integer status = 200;
  private String resBody;
  private Integer elapsed; //in milliseconds
  private Boolean isSlow = Boolean.FALSE;

  private Date createdAt;

  @JsonIgnore
  private Long accountId;
}
