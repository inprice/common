package io.inprice.common.formula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class EvaluationResult {

	private double value;
	private double lowerLimit;
	private double upperLimit;
	
	private String problem;

}
