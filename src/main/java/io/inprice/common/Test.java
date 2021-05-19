package io.inprice.common;

import java.util.Iterator;

import org.jdbi.v3.core.Handle;

import io.inprice.common.converters.GroupRefreshResultConverter;
import io.inprice.common.helpers.Database;
import io.inprice.common.info.GroupRefreshResult;
import io.inprice.common.repository.CommonDao;

public class Test {
  
  public static void main(String[] args) {
  	String[] strs = {
			"/login/search/kes",
			"/login/search",
			"/login",
			"/search",
			"/LOGIN/SEARCH/KES",
			"/LOGIN/SEARCH",
			"/SEARCH",
  	};
  	for (String s : strs) {
			System.out.println(s.matches(""));
		}
  }

}