package io.inprice.common.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueueDef {

	@JsonProperty("active")
	public Boolean ACTIVE = Boolean.TRUE;

	@JsonProperty("name")
	public String NAME;

	@JsonProperty("capacity")
	public int CAPACITY = 0;
	
}
