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
  private BigDecimal price = BigDecimal.ZERO;
  private String minPlatform = "NA";
  private String minSeller = "NA";
  private BigDecimal minPrice = BigDecimal.ZERO;
  private BigDecimal minDiff = BigDecimal.ZERO;
  private BigDecimal avgPrice = BigDecimal.ZERO;
  private BigDecimal avgDiff = BigDecimal.ZERO;
  private String maxPlatform = "NA";
  private String maxSeller = "NA";
  private BigDecimal maxPrice = BigDecimal.ZERO;
  private BigDecimal maxDiff = BigDecimal.ZERO;
  private Integer links = 0;
  private Integer position = 3;
  private Integer ranking;
  private Integer rankingWith = 0; //the number of other links having the same price
  private BigDecimal suggestedPrice = BigDecimal.ZERO;
  private Long companyId;
  private Date createdAt;

}
