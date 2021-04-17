package io.inprice.common.meta;

/**
 * Status Groups of Links
 * 
 * @author mdpinar
 *
 */
public enum LinkStatusGroup {
  
  //candidate group waiting to be handled
	WAITING,

  //healthy group checking data continuously
	ACTIVE,

  //trying group having problem but also checking data a few more times
	TRYING,

  //problem group never be handled again
	PROBLEM;

}