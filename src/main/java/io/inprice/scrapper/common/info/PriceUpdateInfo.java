package io.inprice.scrapper.common.info;

import io.inprice.scrapper.common.models.Link;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Info model for notification of deleted links or links whose prices changed
 *
 * @author mdpinar
 */
@Getter
@Setter
public class PriceUpdateInfo implements Serializable {

  private static final long serialVersionUID = 1390654039767500676L;

  // true means the links is deleted, otherwise, price of the link is updated
  private boolean isDeleted;
  private Long linkId;
  private Long companyId;
  private Long productId;
  private BigDecimal newPrice;

  public PriceUpdateInfo(Link link) {
    this(false, link.getId(), link.getProductId(), link.getCompanyId());
    this.newPrice = link.getPrice();
  }

  public PriceUpdateInfo(boolean isDeleted, Long linkId, Long productId, Long companyId) {
    this.isDeleted = isDeleted;
    this.linkId = linkId;
    this.productId = productId;
    this.companyId = companyId;
  }

}
