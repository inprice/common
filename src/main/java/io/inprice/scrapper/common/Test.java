package io.inprice.scrapper.common;

import io.inprice.scrapper.common.utils.NumberUtils;

public class Test {
  
  public static void main(String[] args) {
    System.out.println(NumberUtils.extractPrice("1"));
    System.out.println(NumberUtils.extractPrice(".12 TL "));
    System.out.println(NumberUtils.extractPrice(".1 TL "));
    System.out.println(NumberUtils.extractPrice("1, TL "));
    System.out.println(NumberUtils.extractPrice("11. TL "));
    System.out.println(NumberUtils.extractPrice("1,2 TL "));
    System.out.println(NumberUtils.extractPrice("1.22 TL "));
    System.out.println(NumberUtils.extractPrice("3.453.451. TL "));
  }

}