package io.inprice.scrapper.common.utils;

public class StringUtils {

  public static String fixQuotes(String raw) {
    return raw.replaceAll("((?<=(\\{|\\[|\\,|:))\\s*')|('\\s*(?=(\\}|(\\])|(\\,|:))))", "\"");
  }

}
