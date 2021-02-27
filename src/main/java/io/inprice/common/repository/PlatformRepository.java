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

  private static final Logger log = LoggerFactory.getLogger(PlatformRepository.class);

  public static Platform findByUrl(Handle handle, String url) {
    Platform found = null;

    String domain = URLUtils.extractDomain(url);
    if (domain != null) {
      try {
        List<String> domainList = new ArrayList<>(1);
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
        log.error("Failed to find platform", e);
      }
    } else {
      log.warn("Domain not found for {}", (url.length() > 50 ? url.substring(0, 50) : url));
    }
    return found;
  }

  public static void main(String[] args) {
    String url = "https://www.apple.com/us-hed/shop/buy-mac/macbook-air/space-gray-apple-m1-chip-with-8%E2%80%91core-cpu-and-7%E2%80%91core-gpu-256gb";
    System.out.println(findByUrl(null, url));
  }

}
