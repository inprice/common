package io.inprice.scrapper.common.info;

import java.io.Serializable;

import io.inprice.scrapper.common.meta.LinkStatus;
import io.inprice.scrapper.common.models.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Info model class for status change notifications
 *
 * @author mdpinar
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StatusChange implements Serializable {

  private static final long serialVersionUID = -7240482576220187098L;

  private Link link;
  private LinkStatus oldStatus;

  public StatusChange(Link link, LinkStatus oldStatus) {
    this.link = link;
    this.oldStatus = oldStatus;
  }

}
