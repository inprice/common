package io.inprice.common.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.Level;
import io.inprice.common.meta.LinkStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkSummary {
  
  private Long id;
  private LinkStatus status;
  private Level level;
  private String platform;
  private String seller;
  private BigDecimal price;
  private Integer ranking;
  private BigDecimal groupPrice;

  @JsonIgnore
  private Long accountId;

}
