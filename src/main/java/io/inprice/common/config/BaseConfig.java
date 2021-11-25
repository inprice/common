package io.inprice.common.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseConfig {

	@JsonProperty("mysql")
	public MysqlConf MYSQL_CONF;

	@JsonProperty("rabbit")
	public RabbitConf RABBIT_CONF;

	@JsonProperty("redis")
	public RedisConf REDIS_CONF;

}
