package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.inprice.common.info.PlanFeature;
import io.inprice.common.meta.PlanType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Plan implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Integer id;
  private PlanType type;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer userLimit;
  private Integer linkLimit;
  private Integer alarmLimit;
  private Date createdAt = new Date();

  //transients
  private List<PlanFeature> features;
  
}
