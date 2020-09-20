package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.inprice.common.meta.CompetitorStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Competitor implements Serializable {

  private static final long serialVersionUID = 2206190984817621818L;

  private Long id;
  private String url;
  private String urlHash;
  private String sku;
  private String name;
  private String brand;
  private String seller;
  private String shipment;
  private BigDecimal price = BigDecimal.ZERO;
  private Integer position;
  private Date lastUpdate;
  private Date lastCheck;
  private CompetitorStatus status = CompetitorStatus.TOBE_CLASSIFIED;
  private CompetitorStatus preStatus = CompetitorStatus.TOBE_CLASSIFIED;
  private Integer retry;
  private Integer httpStatus;
  private String websiteClassName;
  private Long productId;
  private Long siteId;
  private Long companyId;
  private Date createdAt;

  //transients
  private String platform;
  private BigDecimal productPrice = BigDecimal.ZERO;

  private List<CompetitorPrice> priceList;
  private List<CompetitorSpec> specList;
  private List<CompetitorHistory> historyList;

  public Competitor(String url) {
    this.url = url;
  }

}
