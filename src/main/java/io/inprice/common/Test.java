package io.inprice.common;

public class Test {
  
	public static void main(String[] args) {
		String s = java.time.format.DateTimeFormatter.ofPattern("MMM dd, HH:mm").withZone(java.time.ZoneId.of("Europe/Berlin")).format(new java.util.Date().toInstant());
  	
  	System.out.println(s);
  }

}