package io.inprice.common.info;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLink {
  
  private Long id;
  private String platform;
  private String seller;
  private BigDecimal price;
  private BigDecimal productPrice;
  private Integer position;
  private Integer ranking;
  private Long productId;

  @JsonIgnore
  private Long companyId;

}
