package io.inprice.common.formula;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.inprice.common.info.ProductRefreshResult;
import io.inprice.common.meta.Position;
import io.inprice.common.models.SmartPrice;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import net.objecthunter.exp4j.function.Function;

/**
 * Calculates and verifies smart price formulas' expressions
 * 
 * Formula variables;
 * 	p --> Product Price
 *  i --> Minimum Price
 *  a --> Average Price
 *  x --> Maximum Price
 *  
 * @since 2021-10-16
 * @author mdpinar
 */
public class FormulaHelper {

	private static final Function min;
	private static final Function max;

	//necessary functions
	static {
  	min = new Function("min", 2) {
      @Override
      public double apply(double... args) {
          return Math.min(args[0], args[1]);
      }
  	};
  	max = new Function("max", 2) {
      @Override
      public double apply(double... args) {
          return Math.max(args[0], args[1]);
      }
  	};
	}

	/**
	 * Verifies the formulas and returns problems if any
	 * 
	 * @param SmartPriceDTO dto
	 * @return problems, if any
	 */
  public static String verify(SmartPriceDTO dto) {
  	ProductRefreshResult prr = new ProductRefreshResult();
  	prr.setPosition(Position.AVERAGE);
  	prr.setActives(3);
		prr.setProductPrice(BigDecimal.valueOf(110));
		prr.setMinPrice(BigDecimal.valueOf(100));
		prr.setAvgPrice(BigDecimal.valueOf(150));
		prr.setMaxPrice(BigDecimal.valueOf(200));

		SmartPrice sp = new SmartPrice();
		sp.setFormula(dto.getFormula());
		sp.setLowerLimitFormula(dto.getLowerLimitFormula());
		sp.setUpperLimitFormula(dto.getUpperLimitFormula());

		EvaluationResult result = evaluate(sp, prr);
  	return result.getProblem();
  }

  /**
   * Evaluates formulas and returns main formula value
   * 
	 * @param SmartPriceDTO dto
   * @param ProductRefreshResult prr
   * @return main value as double
   */
  public static EvaluationResult evaluate(SmartPrice smartPrice, ProductRefreshResult prr) {
  	if (smartPrice == null || smartPrice.getFormula() == null || prr.getActives().compareTo(0) == 0) {
  		return new EvaluationResult(0, 0, 0, null);
  	}

  	//assignings the prices
  	Map<String, Double> variablesMap = Map.of(
			"p", prr.getProductPrice().doubleValue(),
			"i", prr.getMinPrice().doubleValue(),
			"a", prr.getAvgPrice().doubleValue(),
			"x", prr.getMaxPrice().doubleValue()
		);

  	//to check each problem separately we need to look one by one 
  	String mainProblem = null;
  	String lowerLimitProblem = null;
  	String upperLimitProblem = null;

  	Expression mainExp = null;
  	Expression lowerLimitExp = null;
  	Expression upperLimitExp = null;

  	double mainValue = 0;
  	double lowerLimitValue = 0;
  	double upperLimitValue = 0;

  	//checking main formula against arithmetic errors
  	try {
			mainExp = generateExpression(smartPrice.getFormula(), variablesMap);
		} catch (Exception e) {
			if (e instanceof EmptyStackException) {
				mainProblem = "Invalid formula";
			} else {
				mainProblem = e.getMessage();
			}
		}

  	//if given, checking lower limit formula
		if (StringUtils.isNotBlank(smartPrice.getLowerLimitFormula())) {
	  	try {
	  		lowerLimitExp = generateExpression(smartPrice.getLowerLimitFormula(), variablesMap);
			} catch (Exception e) {
				if (e instanceof EmptyStackException) {
					lowerLimitProblem = "Invalid formula";
				} else {
					lowerLimitProblem = e.getMessage();
				}
			}
		}

  	//if given, checking upper limit too
		if (StringUtils.isNotBlank(smartPrice.getUpperLimitFormula())) {
	  	try {
	  		upperLimitExp = generateExpression(smartPrice.getUpperLimitFormula(), variablesMap);
			} catch (Exception e) {
				if (e instanceof EmptyStackException) {
					upperLimitProblem = "Invalid formula";
				} else {
					upperLimitProblem = e.getMessage();
				}
			}
		}

		//main formula may encounter a problem during evaluation like division by zero!
		if (mainProblem == null) {
	  	ValidationResult mainRes = mainExp.validate();
	  	if (mainRes.isValid()) {
		  	try {
		  		mainValue = mainExp.evaluate();
				} catch (ArithmeticException e) {
					mainProblem = e.getMessage();
				}
	  	} else {
				mainProblem = StringUtils.join(mainRes.getErrors(), ",");
			}
		}

		//the same check as above for lower limit formula
		if (StringUtils.isNotBlank(smartPrice.getLowerLimitFormula()) && lowerLimitProblem == null) {
	  	ValidationResult lowerLimitRes = lowerLimitExp.validate();
	  	if (lowerLimitRes.isValid()) {
	  		try {
	  			lowerLimitValue = lowerLimitExp.evaluate();
				} catch (ArithmeticException e) {
					lowerLimitProblem = e.getMessage();
				}
			} else {
				lowerLimitProblem = StringUtils.join(lowerLimitRes.getErrors(), ",");
	  	}
		}
	
		//the same check as above for upper limit formula
		if (StringUtils.isNotBlank(smartPrice.getUpperLimitFormula()) && upperLimitProblem == null) {
	  	ValidationResult upperLimitRes = upperLimitExp.validate();
	  	if (upperLimitRes.isValid()) {
	  		try {
	  			upperLimitValue = upperLimitExp.evaluate();
				} catch (ArithmeticException e) {
					upperLimitProblem = e.getMessage();
				}
			} else {
				upperLimitProblem = StringUtils.join(upperLimitRes.getErrors(), ",");
			}
		}

		//negative value controls
  	if (mainProblem == null && mainValue < 0) mainProblem = "Negative value";
  	if (lowerLimitProblem == null && lowerLimitValue < 0) lowerLimitProblem = "Negative value";
  	if (upperLimitProblem == null && upperLimitValue < 0) upperLimitProblem = "Negative value";

  	//building the problem statement
  	String problem = null;
  	if (mainProblem != null) {
  		mainValue = 0;
  		problem = mainProblem;
  	}

  	if (lowerLimitProblem != null) {
  		lowerLimitValue = 0;
  		if (problem == null)
  			problem = "Lower limit: " + lowerLimitProblem;
  		else
  			problem = problem + ", Lower limit: " + lowerLimitProblem;
  	}

  	if (upperLimitProblem != null) {
  		upperLimitValue = 0;
  		if (problem == null)
  			problem = "Upper limit: " + upperLimitProblem;
  		else
  			problem = problem + ", Upper limit: " + upperLimitProblem;
  	}

  	//limit controls
		if (lowerLimitValue > 0 && mainValue < lowerLimitValue) mainValue = lowerLimitValue;
		if (upperLimitValue > 0 && mainValue > upperLimitValue) mainValue = upperLimitValue;
		
		//rounding
		if (mainValue > 0) mainValue = Math.round(mainValue*100.0)/100.0;
		if (lowerLimitValue > 0) lowerLimitValue = Math.round(lowerLimitValue*100.0)/100.0;
		if (upperLimitValue > 0) upperLimitValue = Math.round(upperLimitValue*100.0)/100.0;

		//trimming unecessary words from the error message
		if (problem != null) problem = problem.replaceAll(" detected. Please check the expression", "");

  	return new EvaluationResult(mainValue, lowerLimitValue, upperLimitValue, problem);
  }
  
  private static Expression generateExpression(String formula, Map<String, Double> variablesMap) {
  	return new ExpressionBuilder(formula)
        .variables(variablesMap.keySet())
        .functions(min, max)
        .build()
        .setVariables(variablesMap);
  }

  /**
   * Test method
   * 
   * @param args
   */
  public static void main(String[] args) {
  	SmartPriceDTO dto = new SmartPriceDTO();
  	dto.setFormula("min((p*1.10)+0.75,a)"); //returns which one is less; a) 10% more of the price plus 75 cent, b) average
  	dto.setLowerLimitFormula("(i-(i*10/100))"); //cannot be less than minimum minus 10% of minimum  
  	dto.setUpperLimitFormula("a"); //average is the upper limit

		System.out.println(verify(dto));
	}

}
