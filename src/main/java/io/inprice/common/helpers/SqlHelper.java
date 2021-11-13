package io.inprice.common.helpers;

import org.apache.commons.lang3.StringUtils;

public class SqlHelper {

  // http://www.java2s.com/Code/Java/Database-SQL-JDBC/EscapeSQL.htm
  public static String clear(String val) {
    if (StringUtils.isBlank(val)) return val;

    String newVal = val.trim();
    int length = newVal.length();
    int newLength = length;
    // first check for characters that might
    // be dangerous and calculate a length
    // of the string that has escapes.
    for (int i = 0; i < length; i++) {
      char c = newVal.charAt(i);
      switch (c) {
        case '\\':
        case '\"':
        case '\'':
        case '\0': {
          newLength += 1;
        }
        break;
      }
    }
    // nothing to escape in the string
    if (length == newLength) {
      return newVal;
    }

    StringBuffer sb = new StringBuffer(newLength);
    for (int i = 0; i < length; i++) {
      char c = newVal.charAt(i);
      switch (c) {
        case '\\': {
          sb.append("\\\\");
        }
          break;
        case '\"': {
          sb.append("\\\"");
        }
          break;
        case '\'': {
          sb.append("\\\'");
        }
          break;
        case '\0': {
          sb.append("\\0");
        }
          break;
        default: {
          sb.append(c);
        }
      }
    }
    return sb.toString().trim();
  }

  public static void main(String[] args) {
		String line1 = "006,\"Çamaşır Teli, 10 Lu Paket 10\",50, ,TEMİZLİK";
		String line2 = "005,Zımba Teli 10 Lu Paket 10\",30,,KIRTASİYE";
		String line3 = "005,Zımba Teli 10 Lu Paket 10\\\",30,,KIRTASİYE";
		String line4 = "005,Zımba Teli 10 Lu Paket 10\\\\\",30,,KIRTASİYE";
		System.out.println(clear(line1));
		System.out.println(clear(line2));
		System.out.println(clear(line3));
		System.out.println(clear(line4));
	}
  
}
