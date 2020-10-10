package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.common.meta.SubsStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company implements Serializable {

  private static final long serialVersionUID = 1818360516258349831L;

  private Long id;
  private String name;
  private String currencyCode;
  private String currencyFormat;
  private Integer productLimit = 0;
  private Integer productCount = 0;
  private Long adminId;
  private Integer planId;
  private String subsId;
  private SubsStatus subsStatus;
  private Date subsRenewalAt;
  private String subsCustomerId;
  private String title;
  private String address1;
  private String address2;
  private String postcode;
  private String city;
  private String state;
  private String country;
  private Date createdAt = new Date();

}
