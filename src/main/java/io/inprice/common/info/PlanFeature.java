package io.inprice.common.info;

import io.inprice.common.framework.Exclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanFeature {

	@Exclude
	private Integer id;
  private String description;
  private Boolean allowed;
  private Integer orderNo;

}
