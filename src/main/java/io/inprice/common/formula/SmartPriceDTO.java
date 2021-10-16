package io.inprice.common.formula;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmartPriceDTO implements Serializable {

	private static final long serialVersionUID = 5315727280574845920L;

	private Long id;
	private String name;
	private String formula;
	private String lowerLimitFormula;
	private String upperLimitFormula;

}
