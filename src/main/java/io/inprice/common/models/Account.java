package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account implements Serializable {

  private static final long serialVersionUID = 1818360516258349831L;

  @JsonIgnore
  private Long id;
  private String name;
  private String currencyCode;
  private String currencyFormat;
  private Integer productLimit = 0;
  private Integer productCount = 0;

  @JsonIgnore
  private Long adminId;

  private AccountStatus status;
  private Date lastStatusUpdate = new Date();
  private String planName;

  @JsonIgnore
  private String custId;
  @JsonIgnore
  private String subsId;
  private Date subsStartedAt;
  private Date renewalAt;

  private String title;
  private String address1;
  private String address2;
  private String postcode;
  private String city;
  private String state;
  private String country;
  private Date createdAt = new Date();

}
