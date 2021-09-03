package io.inprice.common.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RabbitConf {

	@JsonProperty("host")
	public String HOST;

	@JsonProperty("port")
	public Integer PORT;

	@JsonProperty("username")
	public String USERNAME;

	@JsonProperty("password")
	public String PASSWORD;
	
}
