package io.inprice.common.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmartPrice extends BaseModel {

	private static final long serialVersionUID = -3430192403656814805L;

	private String name;
	private String formula;
	private String lowerLimitFormula;
	private String upperLimitFormula;

}
