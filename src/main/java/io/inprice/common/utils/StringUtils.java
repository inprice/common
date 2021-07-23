package io.inprice.common.utils;

import java.util.Collection;

import io.inprice.common.helpers.SqlHelper;

public class StringUtils {
	
	private static final String QUOTELESS_CHARS = "((?<=(\\{|\\[|\\,|:))\\s*')|('\\s*(?=(\\}|(\\])|(\\,|:))))";
	private static final String EMOJILESS_CHARS = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
	
	public static String fixQuotes(String raw) {
		return raw.replaceAll(QUOTELESS_CHARS, "\"");
	}

  public static String clearEmojies(String raw) {
    return fixQuotes(raw.replaceAll(EMOJILESS_CHARS, "")).trim();
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
  
  public static <O> String join(String separator, Collection<O> coll) {
    if (coll == null || coll.size() < 1) return null;

    StringBuilder sb = new StringBuilder();
    for (O o: coll) {
    	if (o == null) continue;
    	sb.append(separator);
      sb.append(o.toString());
      sb.append(separator);
      sb.append(",");
    }

    if (sb.length() > 1)
    	return sb.toString().substring(0, sb.length()-1);
    else
    	return "";
  }
  
}
