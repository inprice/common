package io.inprice.scrapper.common.info;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

  private Long id;
  private String code;
  private String name;
  private Long brandId;
  private Long categoryId;
  private BigDecimal price;
  private Long importId;

  private Long companyId;

}
