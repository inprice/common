package io.inprice.common.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlanFeature implements Serializable {
	
	private static final long serialVersionUID = -8501808537796540523L;

	private Integer id;
  private String description;
  private Boolean allowed;
  private Integer orderNo = 1;

}
