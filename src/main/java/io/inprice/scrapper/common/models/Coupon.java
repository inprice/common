package io.inprice.scrapper.common.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Coupon implements Serializable {

  private static final long serialVersionUID = 5125346359199270016L;

  private String code;
  private String description;
  private Integer days;
  private Long planId;
  private Long issuedCompanyId;
  private Date issuedAt;
  private Date createdAt = new Date();

}