package io.inprice.common.info;

import java.io.Serializable;
import java.math.BigDecimal;

import io.inprice.common.models.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Info model class for oldStatus change notifications
 *
 * @author mdpinar
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LinkStatusChange implements Serializable {

  private static final long serialVersionUID = -7240482576220187098L;

  private Link link;
  private ParseStatus newParseStatus;
  private BigDecimal oldPrice;

  public LinkStatusChange(Link link, ParseStatus newParseStatus, BigDecimal oldPrice) {
    this.link = link;
    this.newParseStatus = newParseStatus;
    this.oldPrice = oldPrice;
  }

}
