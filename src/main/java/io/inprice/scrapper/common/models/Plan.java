package io.inprice.scrapper.common.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plan implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer productLimit;
  private Integer orderNo;

}
