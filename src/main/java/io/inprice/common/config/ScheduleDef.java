package io.inprice.common.config;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ScheduleDef {

	@SerializedName("active")
	public Boolean ACTIVE = Boolean.TRUE;

	@SerializedName("every")
	public int EVERY;

	@SerializedName("period")
	public String PERIOD;

	@SerializedName("delay")
	public Integer DELAY = 0;

	@SerializedName("data")
	public Map<String, String> DATA;

}
