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
  
  private BigDecimal price = BigDecimal.ZERO; //if greater than zero then competitiveness starts!
  private Position position = Position.UNKNOWN;
  
  private String minPlatform;
  private String minSeller;
  private BigDecimal minDiff= BigDecimal.ZERO;

  private BigDecimal minPrice = BigDecimal.ZERO;
  private BigDecimal avgPrice = BigDecimal.ZERO;
  private BigDecimal avgDiff = BigDecimal.ZERO;
  
  private String maxPlatform;
  private String maxSeller;
  private BigDecimal maxPrice = BigDecimal.ZERO;
  private BigDecimal maxDiff = BigDecimal.ZERO;

  private Date updatedAt;

  private Long brandId;
  private Long categoryId;
  private Long alarmId;

  private Long smartPriceId;
  private BigDecimal smartPrice = BigDecimal.ZERO;
  private String smartPriceProblem;
  
  //transients
  private String brandName;
  private String categoryName;
  private Alarm alarm;
  private SmartPrice smartPriceModel;

  public int getLinkCount() {
  	return actives+waitings+tryings+problems;
	}
  
}
