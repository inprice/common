package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitorPrice implements Serializable {

  private static final long serialVersionUID = 6818942944451174569L;

  private Long id;
  private Long competitorId;
  private BigDecimal price;
  private Integer position;
  private Long productId;
  private Long companyId;
  private Date createdAt;

  public CompetitorPrice(Long competitorId, BigDecimal price) {
    this.competitorId = competitorId;
    this.price = price;
  }

}
