package io.inprice.common.config;

import com.google.gson.annotations.SerializedName;

public class RedisConf {

	@SerializedName("host")
	public String HOST;

	@SerializedName("port")
	public Integer PORT;

	@SerializedName("username")
	public String USERNAME;

	@SerializedName("password")
	public String PASSWORD;
	
}
