package io.inprice.common.info;

import java.io.Serializable;

import io.inprice.common.meta.CompetitorStatus;
import io.inprice.common.models.Competitor;
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
@ToString
@NoArgsConstructor
public class StatusChange implements Serializable {

  private static final long serialVersionUID = -7240482576220187098L;

  private Competitor competitor;
  private CompetitorStatus oldStatus;

  public StatusChange(Competitor competitor, CompetitorStatus oldStatus) {
    this.competitor = competitor;
    this.oldStatus = oldStatus;
  }

}