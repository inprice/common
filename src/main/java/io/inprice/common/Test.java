package io.inprice.common;

import io.inprice.common.helpers.SiteFinder;

public class Test {
  
  public static void main(String[] args) {
    System.out.println(SiteFinder.findSiteByUrl("www.hepsiburada.com"));
    System.out.println(SiteFinder.findSiteByUrl("http://gittigidiyor.com"));
    System.out.println(SiteFinder.findSiteByUrl("https://amazon.com.tr"));
    System.out.println(SiteFinder.findSiteByUrl("http://sanal.com"));
    System.out.println(SiteFinder.findSiteByUrl("https://zooogle.com"));
  }

}