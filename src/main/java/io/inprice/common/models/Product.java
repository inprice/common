package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product implements Serializable {

  private static final long serialVersionUID = 2010109845985968128L;

  private Long id;
  private String code;
  private String name;
  private BigDecimal price;
  private Integer position;
  private Integer linkCount = 0;
  private Integer ranking = 0;
  private Integer rankingWith = 0; //the number of other links having the same price
  private String minPlatform;
  private String minSeller;
  private BigDecimal minPrice = BigDecimal.ZERO;
  private BigDecimal minDiff = BigDecimal.ZERO;
  private BigDecimal avgPrice = BigDecimal.ZERO;
  private BigDecimal avgDiff = BigDecimal.ZERO;
  private String maxPlatform;
  private String maxSeller;
  private BigDecimal maxPrice = BigDecimal.ZERO;
  private BigDecimal maxDiff = BigDecimal.ZERO;
  private BigDecimal suggestedPrice = BigDecimal.ZERO;
  private Date updatedAt;
  private Date createdAt;

  @JsonIgnore
  private Long accountId;

  //transients
  private List<String> tags;

}
