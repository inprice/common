package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.Level;
import io.inprice.common.meta.LinkStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Link implements Serializable {

  private static final long serialVersionUID = 2206190984817621818L;

  private Long id;
  private String url;
  private String sku;
  private String name;
  private String brand;
  private String seller;
  private String shipment;
  private BigDecimal price = BigDecimal.ZERO;
  private Integer ranking = 0;
  private Level level = Level.UNSPECIFIED;
  private LinkStatus preStatus = LinkStatus.TOBE_CLASSIFIED;
  private LinkStatus status = LinkStatus.TOBE_CLASSIFIED;
  private Date lastUpdate;
  private Date lastCheck;
  private String problem;
  private Integer retry;
  private Integer httpStatus;
  private Long platformId;
  private Long groupId;
  private Date createdAt;
  
  @JsonIgnore
  private String urlHash;

  @JsonIgnore
  private Long accountId;

  //transients
  private BigDecimal groupPrice;

  private List<LinkPrice> priceList;
  private List<LinkSpec> specList;
  private List<LinkHistory> historyList;
  
  @JsonIgnore
  private Platform platform;

  public Link(String url) {
    this.url = url;
  }

}
