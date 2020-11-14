package io.inprice.common.info;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Site implements Serializable {

  private static final long serialVersionUID = -6827311486918410398L;

  private Boolean active = Boolean.TRUE;
  private String name;
  private String domain;
  private String country;
  private String className;
  private String status; //if it is not null then every link link pointing this site will be having the same status here!

}
