package io.inprice.common.models;

import java.math.BigDecimal;
import java.util.Date;

import io.inprice.common.meta.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel {

  private static final long serialVersionUID = 2010109845985968128L;

  private String sku;
  private String name;

  private Integer actives = 0;
  private Integer waitings = 0;
  private Integer tryings = 0;
  private Integer problems = 0;
  
  private BigDecimal price = BigDecimal.ZERO;
  private BigDecimal basePrice = BigDecimal.ZERO;
  private Position position = Position.NotSet;
  
  private String minPlatform;
  private String minSeller;
  private BigDecimal minPrice = BigDecimal.ZERO;
  private BigDecimal minDiff= BigDecimal.ZERO;

  private BigDecimal avgPrice = BigDecimal.ZERO;
  private BigDecimal avgDiff = BigDecimal.ZERO;
  
  private String maxPlatform;
  private String maxSeller;
  private BigDecimal maxPrice = BigDecimal.ZERO;
  private BigDecimal maxDiff = BigDecimal.ZERO;

  private Long alarmId;
  private Boolean tobeAlarmed;
  private Date alarmedAt;
  
  private Long smartPriceId;
  private BigDecimal suggestedPrice = BigDecimal.ZERO;
  private String suggestedPriceProblem;

  private Long brandId;
  private Long categoryId;

  private Date updatedAt;
  
  //transients
  private String alarmName;
  private String smartPriceName;

  private String brandName;
  private String categoryName;

  public int getLinkCount() {
  	return actives+waitings+tryings+problems;
	}
  
}
