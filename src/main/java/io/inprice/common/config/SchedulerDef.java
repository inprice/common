package io.inprice.common.config;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchedulerDef {

	@JsonProperty("active")
	public Boolean ACTIVE = Boolean.TRUE;

	@JsonProperty("every")
	public Integer EVERY;

	@JsonProperty("period")
	public String PERIOD;

	@JsonProperty("delay")
	public Integer DELAY = 0;

	@JsonProperty("data")
	public Map<String, Object> DATA;

}
