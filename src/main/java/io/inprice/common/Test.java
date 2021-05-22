package io.inprice.common;public class Test {
  
  public static void main(String[] args) {
  	String[] strs = {
			"{\"status\":113,\"reason\":\"Invalid email or password!\",\"ok\":false}",
			"{\"Status\":114,\"reason\":\"Invalid email or password!\",\"ok\":false}",
			"{\"ktatus\":115,\"reason\":\"Invalid email or password!\",\"ok\":false}",
			"{\"reason\":\"Invalid email or password!\",\"status\":116,\"ok\":false}",
  	};
  	String indicator = "status\":";
  	for (String s : strs) {
  		int first = s.toLowerCase().indexOf(indicator);
  		if (first > -1) {
    		int last = s.toLowerCase().indexOf(",", first+indicator.length());
  			System.out.println(s.substring(first+indicator.length(), last));
  		}
		}
  }

}