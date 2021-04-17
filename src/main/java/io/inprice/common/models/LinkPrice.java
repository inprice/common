package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkPrice implements Serializable {

  private static final long serialVersionUID = 6818942944451174569L;

  private Long id;
  private Long linkId;
  private BigDecimal price;
  private BigDecimal diffAmount;
  private BigDecimal diffRate;
  private Long groupId;
  private Date createdAt;
  
  @JsonIgnore
  private Long accountId;

  public LinkPrice(Long linkId, BigDecimal price) {
    this.linkId = linkId;
    this.price = price;
  }

}
