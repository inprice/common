package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Plan implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Long id;
  private Boolean active = Boolean.TRUE;
  private Integer orderNo;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer productLimit;
  private String stripeProdId;

}
