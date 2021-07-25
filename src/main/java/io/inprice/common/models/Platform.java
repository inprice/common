package io.inprice.common.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.LinkStatus;
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
  @JsonIgnore
  private LinkStatus status; //when not null, every link pointing this platform will be having the same status!
  @JsonIgnore
  private String problem;

}
