package io.inprice.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.inprice.common.helpers.SqlHelper;

public class StringHelper {
	
	private static final String QUOTELESS_CHARS = "((?<=(\\{|\\[|\\,|:))\\s*')|('\\s*(?=(\\}|(\\])|(\\,|:))))";
	private static final String EMOJILESS_CHARS = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";
	private static final String CSV_SPLITTER = "(?<!\".*[^\"]),|,(?![^\"].*\")";
	
	public static String fixQuotes(String raw) {
		return raw.replaceAll(QUOTELESS_CHARS, "\"");
	}

  public static String clearEmojies(String raw) {
    return fixQuotes(raw.replaceAll(EMOJILESS_CHARS, "")).trim();
  }

  public static String clearErrorMessage(String message) {
  	String problem = null;
  	if (message != null && message.trim().isEmpty() == false) {
  		problem = SqlHelper.clear(message);
  		if (problem.length() > 255) {
  			problem = problem.substring(0, 255);
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

  /**
   * Splits given csv row by comma. Double quotes are considered for the columns having comma in it
   * 
   * @param csv row
   * @return column list
   */
	public static List<String> splitCSV(String row) {
		List<String> result = new ArrayList<>();
		String[] columns = row.split(CSV_SPLITTER);
		for (String column: columns) {
			if (StringUtils.isNotBlank(column) && column.charAt(0) == '"' && column.charAt(column.length()-1) == '"') {
				result.add(column.substring(1, column.length()-1).trim());
			} else {
				result.add(column.trim());
			}
		}
		return result;
	}

  public static String escapeJSON(String json) {
    return json
      .replaceAll("\n", " ")
      .replaceAll("\r", "");     
  }

  public static void main(String[] args) {
		String line1 = "006,\"Çamaşır Teli, 10 Lu Paket 10\",50, ,TEMİZLİK";
		String line2 = "005,Zımba Teli 10 Lu Paket 10\",30,,KIRTASİYE";
		System.out.println(splitCSV(line1));
		System.out.println(splitCSV(line2));
	}

}
