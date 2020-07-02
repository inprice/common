package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPrice implements Serializable {

  private static final long serialVersionUID = 5103850978461831401L;

  private Long id;
  private Long productId;
  private BigDecimal price;
  private String minPlatform;
  private String minSeller;
  private BigDecimal minPrice;
  private BigDecimal minDiff;
  private BigDecimal avgPrice;
  private BigDecimal avgDiff;
  private String maxPlatform;
  private String maxSeller;
  private BigDecimal maxPrice;
  private BigDecimal maxDiff;
  private Integer competitors;
  private Integer position;
  private Integer ranking;
  private Integer rankingWith; //the number of other competitors having the same price
  private BigDecimal suggestedPrice;
  private Long companyId;
  private Date createdAt;

}
