package io.inprice.common.models;

import java.io.Serializable;

import io.inprice.common.framework.Exclude;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.QueueName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Platform implements Serializable {

  private static final long serialVersionUID = -6827311486918410398L;

  @Exclude
  private Long id;
  private String name;

  @Exclude
  private String domain;
  private String country;

  @Exclude
  private String className;
  private String currencyCode;
  private String currencyFormat;

  //some websites must be handled than the others. to be able to do this, we manage them in a separated queue (singly queue)
  @Exclude
  private QueueName queue;
  
  @Exclude
  private LinkStatus status; //if not null, every link pointing this platform will be having the same status!

}
