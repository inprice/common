package io.inprice.common.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkPrice extends BaseModel {

  private static final long serialVersionUID = 6818942944451174569L;

  private Long linkId;
  private BigDecimal oldPrice;
  private BigDecimal newPrice;
  private BigDecimal diffAmount;
  private BigDecimal diffRate;
  private Long productId;

  public LinkPrice(Long linkId, BigDecimal oldPrice, BigDecimal newPrice) {
    this.linkId = linkId;
    this.oldPrice = oldPrice;
    this.newPrice = newPrice;
  }

}
