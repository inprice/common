package io.inprice.common.utils;

import io.inprice.common.helpers.SqlHelper;

public class StringUtils {

  public static String fixQuotes(String raw) {
    return raw.replaceAll("((?<=(\\{|\\[|\\,|:))\\s*')|('\\s*(?=(\\}|(\\])|(\\,|:))))", "\"");
  }

  public static String clearErrorMessage(String message) {
  	String problem = null;
  	if (message != null && ! message.isEmpty()) {
  		problem = SqlHelper.clear(message);
  		if (problem.length() > 250) {
  			problem = problem.substring(0, 250);
  		}
  
  		if (problem.indexOf("\n") > -1) {
  			String[] parts = problem.split("\n");
  			problem = parts[0];
  		}

  	}
  	return problem != null ? SqlHelper.clear(problem) : problem;
  }
  
}
