package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LinkGroup implements Serializable {

  private static final long serialVersionUID = 2010109845985968128L;

  private Long id;
  private String name;

  private Integer actives = 0;
  private Integer waitings = 0;
  private Integer tryings = 0;
  private Integer problems = 0;
  
  private BigDecimal price = BigDecimal.ZERO; //if greater than zero then competitiveness starts!
  private Level level = Level.NA;
  private BigDecimal total = BigDecimal.ZERO;
  
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

  @JsonIgnore
  private Long accountId;
  
  private Date updatedAt;
  private Date createdAt;

  public int getLinkCount() {
  	return actives+waitings+tryings+problems;
	}
  
}