package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Plan implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private String features;
  private Integer linkLimit;
  private Integer alarmLimit;
  private Date createdAt = new Date();

}
