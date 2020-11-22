package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.UserRole;
import io.inprice.common.meta.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member implements Serializable {

  private static final long serialVersionUID = -7793356216234713734L;

  private Long id;
  private String email;
  private Long userId;
  private UserRole role;
  private UserStatus status = UserStatus.PENDING;
  private UserStatus preStatus = UserStatus.PENDING;
  private Integer retry = 1;
  private Date updatedAt;
  private Date createdAt = new Date();

  @JsonIgnore
  private Long companyId;

  // transient
  private String companyName;
  private Boolean freeUsage;
  private String planName;
  private String subsStatus;
  private Date subsRenewalAt;
  private String currencyFormat;
  private Integer productCount;

}
