package io.inprice.common.meta;

/**
 * DEF prefix is short for DEFAULT. 
 * 
 * Some urls may need to be called from where they located (Germany or Canada)
 * In this situation, we will be using their country code as prefixes like GER or CAN
 * 
 * @author mdpinar
 *
 */
public enum PlatformQueue {

	//used as default queue for those links which can be handled concurrently (ebay, amazon...)
	DEF_MULTIPLE,

	//used for those links which must be handled only one at a time (walmart.ca)
	DEF_SINGLE;

}
