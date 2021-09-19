package io.inprice.common.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * workspaceId is set only when super user switches workspace!
 * 
 * @author mdpinar
 */
@Getter
@Setter
public class User extends BaseModel {

  private static final long serialVersionUID = 1753526228909974777L;

  private String email;
  private String name;
  private String timezone;
  private boolean privileged;
  private boolean banned;
  private Date bannedAt;
  private String banReason;

  @JsonIgnore
  private String password;

  private Long accid; //for only super user!
  
}
