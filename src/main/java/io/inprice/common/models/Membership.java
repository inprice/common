package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.common.meta.SubsStatus;
import io.inprice.common.meta.UserRole;
import io.inprice.common.meta.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Membership implements Serializable {

  private static final long serialVersionUID = -7793356216234713734L;

  private Long id;
  private String email;
  private Long userId;
  private Long companyId;
  private UserRole role;
  private UserStatus status = UserStatus.PENDING;
  private UserStatus preStatus = UserStatus.PENDING;
  private Integer retry = 1;
  private Date updatedAt;
  private Date createdAt = new Date();

  //transients
  private String companyName;
  private String currencyFormat;
  private Long planId;
  private SubsStatus subsStatus;
  private Date subsRenewalAt;

}
