package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.CompanyStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company implements Serializable {

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
  private String planName;

  private CompanyStatus status;
  private Date lastStatusUpdate = new Date();

  @JsonIgnore
  private String subsId;
  @JsonIgnore
  private String subsCustomerId;
  private Date subsRenewalAt;

  private String title;
  private String address1;
  private String address2;
  private String postcode;
  private String city;
  private String state;
  private String country;
  private Date createdAt = new Date();

}
