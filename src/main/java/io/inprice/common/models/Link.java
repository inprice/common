package io.inprice.common.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.Level;
import io.inprice.common.meta.LinkStatus;
import io.inprice.common.meta.LinkStatusGroup;
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
  private Level level = Level.NA;

  @JsonIgnore
  private LinkStatus preStatus = LinkStatus.TOBE_CLASSIFIED;
  private LinkStatus status = LinkStatus.TOBE_CLASSIFIED;
  
  @JsonIgnore
  private String parseCode;
  @JsonIgnore
  private String parseProblem;

  private Integer retry;
  private Boolean watchlisted = Boolean.FALSE;
  
  @JsonIgnore
  private Long platformId;
  private Long groupId;

  private Date updatedAt;
  private Date checkedAt;
  
  @JsonIgnore
  private String urlHash;

  private Long alarmId;

  //transients
  private String groupName;
  private BigDecimal groupPrice;

  private List<LinkPrice> priceList;
  private List<LinkSpec> specList;
  private List<LinkHistory> historyList;

  private Platform platform;
  private Alarm alarm;

  public Link(String url) {
    this.url = url;
  }

  public LinkStatusGroup getStatusGroup() {
  	return status.getGroup();
  }
  
  public String getStatusDescription() {
  	return status.getDescription();
  }

}
