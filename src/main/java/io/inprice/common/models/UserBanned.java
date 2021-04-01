package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBanned implements Serializable {

  private static final long serialVersionUID = -2637012763790476362L;

  private String email;
  private String reason;
  private Date createdAt;

}
