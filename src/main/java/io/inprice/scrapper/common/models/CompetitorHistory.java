package io.inprice.scrapper.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.scrapper.common.meta.CompetitorStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitorHistory implements Serializable {

  private static final long serialVersionUID = 885057701505049080L;

  private Long id;
  private Long competitorId;
  private CompetitorStatus status = CompetitorStatus.TOBE_CLASSIFIED;
  private Integer httpStatus;
  private Long productId;
  private Long companyId;
  private Date createdAt;

  public CompetitorHistory(CompetitorStatus status) {
    this.status = status;
  }

  public CompetitorHistory(Long competitorId, CompetitorStatus status) {
    this.competitorId = competitorId;
    this.status = status;
  }

}
