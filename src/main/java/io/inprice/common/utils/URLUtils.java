package io.inprice.common.utils;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class URLUtils {
	
	private static Set<String> domainsWithLang;
	
	static {
		domainsWithLang = Set.of(
			"apple.com"
		);
	}

  public static final String URL_CHECK_REGEX = "^https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

  public static boolean isAValidURL(String url) {
    if (StringUtils.isBlank(url) || url.trim().length() < 15 || url.trim().length() > 1024)
      return false;
    return url.matches(URL_CHECK_REGEX);
  }

  public static String extractDomain(String url) {
    final String newForm = url.replaceAll("^(https?)://|www.", "");
    final String[] chunks = newForm.split("/");

    if (chunks.length > 0) {
    	if (domainsWithLang.contains(chunks[0])) {
    		return chunks[0] + "/" + chunks[1];
    	}
    	return chunks[0];
    }
    return null;
  }

  public static void main(String[] args) {
  	String[] urls = {
  		"https://www.apple.com/tr/shop/buy-airpods/airpods-max",
  		"https://www.urun.n11.com/lastik-zinciri/matte-kar-corabi-superx-series-P454282185",
  		"https://www.trendyol.com/yatas-projects/joyful-100-dht-yayli-seri-yatak-p-32939343",
  		"https://www.hepsiburada.com/xiaomi-redmi-note-9-pro-128-gb-xiaomi-turkiye-garantili-p-HBV00000TOMQJ?magaza=Hepsiburada",
  		"https://www.amazon.com.tr/Stanley-Klasik-Vakumlu-%C3%87elik-Termos/dp/B07P1T5H5L/ref=sr_1_4?dchild=1&m=A1UNQM1SR2CHM&pf_rd_p=86ad3831-81f7-422d-816c-64a8bc6584c6&pf_rd_r=1DXKDTJASYC22EA0KHM8&qid=1610796480&refinements=p_6%3AA1UNQM1SR2CHM&s=sports&sr=1-4"
  	};
  	
  	for (String url : urls) {
  		String domain = extractDomain(url);
  		System.out.println(domain);
      int segments = StringUtils.countMatches(domain, ".");
      if (segments > 1) {
        int startAt = domain.indexOf(".");
        for (int i = 0; i < segments-1; i++) {
          startAt = domain.indexOf(".", startAt)+1;
          if (startAt > 0 && startAt < domain.length()) {
            System.out.println(domain.substring(startAt));
          } else {
          	break;
          }
        }
      }
		}
   
  }

}
