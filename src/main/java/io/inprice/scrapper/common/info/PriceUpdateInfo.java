package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.models.Competitor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Info model for notification of deleted competitors or competitors whose prices changed
 *
 * @author mdpinar
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PriceUpdateInfo implements Serializable {

  private static final long serialVersionUID = 1390654039767500676L;

  // true means the competitors is deleted, otherwise, price of the competitor is updated
  private boolean isDeleted;
  private Long competitorId;
  private Long companyId;
  private Long productId;
  private BigDecimal newPrice;

  public PriceUpdateInfo(Competitor competitor) {
    this(false, competitor.getId(), competitor.getProductId(), competitor.getCompanyId());
    this.newPrice = competitor.getPrice();
  }

  public PriceUpdateInfo(boolean isDeleted, Long competitorId, Long productId, Long companyId) {
    this.isDeleted = isDeleted;
    this.competitorId = competitorId;
    this.productId = productId;
    this.companyId = companyId;
  }

}
