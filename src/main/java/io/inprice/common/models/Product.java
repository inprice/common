package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product implements Serializable {

  private static final long serialVersionUID = 2010109845985968128L;

  private Long id;
  private Boolean active = Boolean.TRUE;
  private String code;
  private String name;
  private BigDecimal price;
  private Integer position;
  private Long companyId;
  private Long lastPriceId;
  private Date updatedAt;
  private Date createdAt;

  //transients
  private ProductPrice priceDetails;
  private Set<String> tags;

}
