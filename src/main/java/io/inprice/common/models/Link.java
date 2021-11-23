package io.inprice.common.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.Position;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.Grup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Link extends BaseModel {

  private static final long serialVersionUID = 2206190984817621818L;

  private String url;
  private String sku;
  private String name;
  private String brand;
  private String seller;
  private String shipment;
  private BigDecimal price = BigDecimal.ZERO;
  private Integer priceDirection = 0;
  private Position position = Position.NotSet;

  @JsonIgnore
  private LinkStatus preStatus = LinkStatus.TOBE_CLASSIFIED;
  @JsonIgnore
  private LinkStatus status = LinkStatus.TOBE_CLASSIFIED;

  private Grup grup = Grup.WAITING;

  @JsonIgnore
  private String parseCode;
  @JsonIgnore
  private String parseProblem;

  private Integer retry;
  @JsonIgnore
  private Boolean watchlisted = Boolean.FALSE;

  private Long alarmId;
  @JsonIgnore
  private Boolean tobeAlarmed;
  private Date alarmedAt;

  @JsonIgnore
  private Long platformId;
  private Long productId;

  private Date updatedAt;
  private Date checkedAt;
  
  @JsonIgnore
  private String urlHash;

  //transients
  private String productName;
  private BigDecimal productPrice;
  private BigDecimal productBasePrice;
  private Position productPosition;

  private Long productAlarmId;
  private Long productSmartPriceId;

  private List<LinkPrice> priceList;
  private List<LinkSpec> specList;
  private List<LinkHistory> historyList;

  private Platform platform;

  private String alarmName;
  
  public Link(String url) {
    this.url = url;
  }
  
  public String getStatusDescription() {
  	return status.getDescription();
  }

}
