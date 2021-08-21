package io.inprice.common.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MysqlConf {

	@JsonProperty("host")
	public String HOST;

	@JsonProperty("port")
	public Integer PORT;

	@JsonProperty("database")
	public String DATABASE;

	@JsonProperty("username")
	public String USERNAME;

	@JsonProperty("password")
	public String PASSWORD;

	@JsonProperty("additions")
	public String ADDITIONS;

	@JsonProperty("showQueries")
	public Boolean SHOW_QUERIES = Boolean.FALSE;

}
