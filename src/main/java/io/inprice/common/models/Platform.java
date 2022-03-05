package io.inprice.common.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Platform implements Serializable {

  private static final long serialVersionUID = -6827311486918410398L;

  @JsonIgnore
  private Long id;

  private String domain;
  @JsonIgnore
  private String className;
  private String country;

  private String currencyCode;
  private String currencyFormat;
  
  private Boolean parked = Boolean.FALSE; //if it is under maintenance, this value is set TRUE
  private Boolean blocked = Boolean.FALSE; //if website doesn't allow us to scrape, this value is set TRUE

  //some websites must be handled than the others. to be able to do this, we manage them in a separated queue (ie singly queue)
  @JsonIgnore
  private String queue;

}
