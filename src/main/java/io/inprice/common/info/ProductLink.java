package io.inprice.common.info;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLink {
  
  private Long id;
  private String platform;
  private String seller;
  private BigDecimal price;
  private Integer position;
  private Integer ranking;
  private Long productId;
  private Long companyId;

}
