package io.inprice.common.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanFeature {

	@JsonIgnore
	private Integer id;
  private String description;
  private Boolean allowed;
  private Integer orderNo;

}
