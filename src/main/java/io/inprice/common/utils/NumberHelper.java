package io.inprice.common.utils;

import org.apache.commons.lang3.StringUtils;

public class NumberHelper {

  public static String extractPrice(String numString) {
    StringBuilder sb = new StringBuilder();
    for (Character ch : numString.trim().toCharArray()) {
      if ((ch >= '0' && ch <= '9') || ch == ',' || ch == '.')
        sb.append(ch);
    }

    String trimmed = sb.toString();
    if (trimmed.isEmpty()) return "0";

    if (trimmed.charAt(0) == '.' || trimmed.charAt(0) == ',') {
      trimmed = "0" + trimmed;
    }
    if (trimmed.charAt(trimmed.length()-1) == '.' || trimmed.charAt(trimmed.length()-1) == ',') {
      trimmed += "00";
    } else if (trimmed.length() > 2 && (trimmed.charAt(trimmed.length()-2) == '.' || trimmed.charAt(trimmed.length()-2) == ',')) {
      trimmed += "0";
    }      

    boolean commaDecimal = (trimmed.length() > 3
        && (trimmed.charAt(trimmed.length() - 3) == ',' || trimmed.charAt(trimmed.length() - 3) == '.'));

    String pure = trimmed.replaceAll("[^\\d]", "").replaceAll("\\.", "");

    if (commaDecimal) {
      int ix = pure.length() - 2;
      return pure.substring(0, ix) + "." + pure.substring(ix);
    } else {
      if (pure.isEmpty())
        return "0";
      else
        return pure;
    }
  }
  
  public static Integer toInteger(String val) {
    if (StringUtils.isNotBlank(val)) {
      try {
        return Integer.parseInt(val.trim());
      } catch (Exception e) { }
    }
  	return null;
  }

  public static int toInteger(String val, int defauld) {
    if (StringUtils.isNotBlank(val)) {
      try {
        return Integer.parseInt(val.trim());
      } catch (Exception e) { }
    }
    return defauld;
  }

  public static Long toLong(String val) {
    if (StringUtils.isNotBlank(val)) {
  		try {
  			return Long.parseLong(val.trim());
  		} catch (Exception e) { }
  	}
  	return null;
  }
  
  public static long toLong(String val, long defauld) {
    if (StringUtils.isNotBlank(val)) {
      try {
        return Long.parseLong(val.trim());
      } catch (Exception e) { }
    }
    return defauld;
  }

}
