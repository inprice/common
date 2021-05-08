package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountHistory implements Serializable {

  private static final long serialVersionUID = -323250016123276994L;

  @JsonIgnore
  private Long id;
  private Long accountId;
  private AccountStatus status;
  private Integer planId;
  private Date createdAt = new Date();

}
