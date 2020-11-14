package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  private Boolean active;
  private String url;
  private String sku;
  private String name;
  private String brand;
  private String seller;
  private String shipment;
  private BigDecimal price = BigDecimal.ZERO;
  private Integer position;
  private Date lastUpdate;
  private Date lastCheck;
  private LinkStatus status = LinkStatus.TOBE_CLASSIFIED;
  private LinkStatus preStatus = LinkStatus.TOBE_CLASSIFIED;
  private String problem;
  private Integer retry;
  private Integer httpStatus;
  private String className;
  private String platform;
  private Long importDetailId;
  private Long productId;
  private Date createdAt;
  
  @JsonIgnore
  private String urlHash;

  @JsonIgnore
  private Long companyId;

  //transients
  private BigDecimal productPrice;

  private List<LinkPrice> priceList;
  private List<LinkSpec> specList;
  private List<LinkHistory> historyList;

  public Link(String url) {
    this.url = url;
  }

}
