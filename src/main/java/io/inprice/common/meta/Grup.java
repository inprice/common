package io.inprice.common.meta;

/**
 * Status Products of Links
 * 
 * @author mdpinar
 *
 */
public enum Grup {
  
  //candidate product waiting to be handled
	WAITING,

  //healthy product checking data continuously
	ACTIVE,

  //trying product having problem but also checking data a few more times
	TRYING,

  //problem product never be handled again
	PROBLEM;

}