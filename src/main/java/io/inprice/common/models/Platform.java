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
  private String name;

  @JsonIgnore
  private String domain;
  private String country;

  @JsonIgnore
  private String className;
  private String currencyCode;
  private String currencyFormat;

  //some websites must be handled than the others. to be able to do this, we manage them in a separated queue (singly queue)
  @JsonIgnore
  private String queue;
  
  private Boolean parked = Boolean.FALSE; //if it is under maintenance, this value is set TRUE
  private Boolean blocked = Boolean.FALSE; //if website doesn't allow us to scrape, this value is set TRUE

  private String profile;

}
