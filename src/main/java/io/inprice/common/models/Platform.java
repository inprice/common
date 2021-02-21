package io.inprice.common.models;

import java.io.Serializable;

import io.inprice.common.meta.LinkStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Platform implements Serializable {

  private static final long serialVersionUID = -6827311486918410398L;

  private Long id;
  private String name;
  private String domain;
  private String country;
  private String className;
  private LinkStatus status; //if it is not null then every link pointing this site will be having the same status here!
  private String problem;

  private int browserPossibility;
  private boolean loadingMainPageFirst;
  private int extraTimeout;

}
