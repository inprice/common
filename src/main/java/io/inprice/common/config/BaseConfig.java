package io.inprice.common.config;

import com.google.gson.annotations.SerializedName;

public class BaseConfig {

	@SerializedName("env")
	public String ENV = "dev";

	@SerializedName("mysql")
	public MysqlConf MYSQL_CONF;

	@SerializedName("rabbit")
	public RabbitConf RABBIT_CONF;

	@SerializedName("redis")
	public RedisConf REDIS_CONF;

}
