package io.inprice.common.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.inprice.common.meta.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "id", "adminId" })
public class Account extends BaseModel {

  private static final long serialVersionUID = 1818360516258349831L;

  private String name;
  private String title;
  private String address1;
  private String address2;
  private String postcode;
  private String city;
  private String state;
  private String country;

  private String preStatus; //used for returning BANNED status!
  private AccountStatus status;
  private Date lastStatusUpdate = new Date();

  private Integer planId;

  private Integer userCount = 0;
  private Integer linkCount = 0;
  private Integer alarmCount = 0;

  private Date subsStartedAt;
  private Date subsRenewalAt;

  private String currencyCode;
  private String currencyFormat;
  private Boolean demo = Boolean.FALSE;

  private Long adminId;

  //transients
  private Plan plan;
  private String email;
  private Long xid; //id for super user! 

  @Override
  public final Long getAccountId() {
  	return null;
  }
  
  @Override
  public void setAccountId(Long accountId) {
  	;
  }

}
