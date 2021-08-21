package io.inprice.common.config;

import com.google.gson.annotations.SerializedName;

public class QueueDef {

	@SerializedName("active")
	public Boolean ACTIVE = Boolean.TRUE;

	@SerializedName("name")
	public String NAME;

	@SerializedName("capacity")
	public Integer CAPACITY;
	
}
