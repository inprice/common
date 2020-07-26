package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Plan implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer productLimit;
  private String stripePriceId;

  public Plan(Integer id, String name, String description, BigDecimal price, Integer productLimit,
      String stripePriceId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.productLimit = productLimit;
    this.stripePriceId = stripePriceId;
  }

}
