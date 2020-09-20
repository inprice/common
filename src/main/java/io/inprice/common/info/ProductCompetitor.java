package io.inprice.common.info;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCompetitor implements Serializable {

  private static final long serialVersionUID = 373006989454188752L;

  private Long id;
  private Long productId;
  private BigDecimal price;
  private Integer position;
  private String seller;
  private String siteName;
  private Integer ranking = 0;
  private Long companyId;

}
