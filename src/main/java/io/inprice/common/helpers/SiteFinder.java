package io.inprice.common.helpers;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.info.Site;
import io.inprice.common.utils.URLUtils;

public class SiteFinder {

  private static final Logger log = LoggerFactory.getLogger(SiteFinder.class);

  private static Map<String, Site> sitesByDomain;
  private static ObjectMapper mapper = new ObjectMapper();

  static {
    try (InputStream inputStream = SiteFinder.class.getClassLoader().getResourceAsStream("sites.json")) {
      List<Site> sites = mapper.readValue(inputStream, new TypeReference<List<Site>>(){});
      sitesByDomain = new TreeMap<>(Collections.reverseOrder());
      sites.forEach(site -> sitesByDomain.put(site.getDomain(), site));
    } catch (Exception e) {
      log.error("Failed to read site.json file", e);
    }    
  }

  public static Site findSiteByUrl(String url) {
    final String domain = URLUtils.extractDomain(url);
    Site found = null;

    if (domain != null) {
      found = sitesByDomain.get(domain);
      if (found == null) { // if not found
        for (Map.Entry<String, Site> entry : sitesByDomain.entrySet()) {
          if (domain.endsWith(entry.getKey())) {
            found = entry.getValue();
            break;
          }
        }
      }
    }
    return found;
  }

}
