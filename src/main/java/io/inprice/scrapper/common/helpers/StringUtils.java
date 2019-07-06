package io.inprice.scrapper.common.helpers;

public class StringUtils {

    public static String fixQuotes(String raw) {
        return raw.replaceAll("((?<=(\\{|\\[|\\,|:))\\s*')|('\\s*(?=(\\}|(\\])|(\\,|:))))", "\"");
    }

}
