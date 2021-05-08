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
  private String title;
  private String address1;
  private String address2;
  private String postcode;
  private String city;
  private String state;
  private String country;

  private AccountStatus status;
  private Date lastStatusUpdate = new Date();

  private Integer planId;
  private Integer linkCount = 0;
  private Integer alarmCount = 0;

  private Date subsStartedAt;
  private Date subsRenewalAt;

  private String currencyCode;
  private String currencyFormat;
  private Boolean demo = Boolean.FALSE;

  @JsonIgnore
  private Long adminId;

  private Date createdAt = new Date();

  //transients
  private Plan plan;

}
