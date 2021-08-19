package io.inprice.common.utils;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides system level encryption keys
 * The values must be as system env variables!
 * And key names must start with KEY_ word! 
 * 
 * @author mdpinar
 * @since 2021-05-14
 *
 */
public class Secrets {
	
	public enum Realm {

		/*
		 * Used for generating token for super user
		 * Example: ,~2w&RWmV3bchk']pKbC<B)!:F[*#Ss8
		 */
		SUPER_USER,

		/*
		 * Used for generating token for standard users
		 * Example: -8'fq{>As@n77jcx24.U*$=PS]#Z5wY+
		 */
		USER,

		/*
		 * Used for encrpyting strings like the token body of super user's!
		 * Example: f7*$q{>AKbC<B)!s@n7=P
		 */
		ENCRYPTION
	}

	public static byte[] getKey(Realm realm) {
		String key = "KEY_" + realm.name();
  	if (StringUtils.isNotBlank(key)) {
  		String val = System.getenv().get(key);
  		if (StringUtils.isNotBlank(val)) {
  			return val.getBytes(StandardCharsets.UTF_8);
  		}
  	}
  	return null;
  }

}
