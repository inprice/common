package io.inprice.common.config;

import com.google.gson.annotations.SerializedName;

public class MysqlConf {

	@SerializedName("driver")
	public String DRIVER;

	@SerializedName("host")
	public String HOST;

	@SerializedName("port")
	public Integer PORT;

	@SerializedName("database")
	public String DATABASE;

	@SerializedName("username")
	public String USERNAME;

	@SerializedName("password")
	public String PASSWORD;

	@SerializedName("additions")
	public String ADDITIONS;

	@SerializedName("showQueries")
	public Boolean SHOW_QUERIES = Boolean.FALSE;

}
