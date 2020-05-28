package io.inprice.scrapper.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product implements Serializable {

  private static final long serialVersionUID = 2010109845985968128L;

  private Long id;
  private Boolean active = Boolean.TRUE;
  private String code;
  private String name;
  private String brand;
  private String category;
  private BigDecimal price;
  private Integer position;
  private String minPlatform;
  private String minSeller;
  private BigDecimal minPrice;
  private BigDecimal avgPrice;
  private String maxPlatform;
  private String maxSeller;
  private BigDecimal maxPrice;
  private Integer linksCount;
  private Long companyId;
  private Date updatedAt;
  private Date createdAt;

}
