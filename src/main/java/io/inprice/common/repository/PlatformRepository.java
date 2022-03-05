package io.inprice.common.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdbi.v3.core.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.inprice.common.models.Platform;
import io.inprice.common.utils.URLUtils;

public class PlatformRepository {

  private static final Logger logger = LoggerFactory.getLogger(PlatformRepository.class);

  public static Platform findByUrl(Handle handle, String url) {
    Platform found = null;

    String domain = URLUtils.extractDomain(url);
    if (domain != null) {
      try {
        List<String> domainList = new ArrayList<>();
        domainList.add(domain);

        int segments = StringUtils.countMatches(domain, ".");
        if (segments > 1) {
          int startAt = domain.indexOf(".");
          for (int i = 0; i < segments-1; i++) {
            startAt = domain.indexOf(".", startAt)+1;
            if (startAt > 0 && startAt < domain.length()) {
              domainList.add(domain.substring(startAt));
            } else {
              break;
            }
          }
        }
        PlatformDao platformDao = handle.attach(PlatformDao.class);
        found = platformDao.findByDomainList(domainList);
      } catch (Exception e) {
        logger.error("Failed to find platform", e);
      }
    } else {
      logger.warn("Domain not found for {}", (url.length() > 50 ? url.substring(0, 50) : url));
    }
    return found;
  }

  public static void main(String[] args) {
    String url = "https://www.walmart.ca/en/ip/Costway-12-Amp-14-Inch-Electric-Push-Lawn-Corded-Mower-With-Grass-Bag-Green/PRD2QUNYMN8LBHS";
    System.out.println(findByUrl(null, url));
  }

}
