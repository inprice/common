package io.inprice.common.utils;

import org.apache.commons.lang3.StringUtils;

public class URLUtils {

  public static final String URL_CHECK_REGEX = "^https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

  public static boolean isAValidURL(String url) {
    if (StringUtils.isBlank(url) || url.trim().length() < 20 || url.trim().length() > 1024) return false;
    return url.matches(URL_CHECK_REGEX);
  }

  public static String extractDomain(String url) {
    final String newForm = url.replaceAll("^(https?)://|www.", "");
    final String[] chunks = newForm.split("/");

    if (chunks.length > 0)return chunks[0];
    return null;
  }

}
