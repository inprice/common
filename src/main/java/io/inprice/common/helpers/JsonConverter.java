package io.inprice.common.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.inprice.common.framework.FieldExclusionStrategy;

public class JsonConverter {

  private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

  public static final Gson gson = 
  		new GsonBuilder()
  		.setDateFormat("yyyy-MM-dd HH:mm:ss")
  		.setExclusionStrategies(new FieldExclusionStrategy())
  		.create();

  public static <T> T fromJson(String value, Class<T> clazz) {
    try {
      return gson.fromJson(value, clazz);
    } catch (Exception e) {
      logger.error("Failed to parse a json", e);
    }
    return null;
  }

  public static <T> String toJson(T value) {
    try {
      return gson.toJson(value);
    } catch (Exception e) {
      logger.error("Failed to convert a json to string", e);
    }
    return null;
  }

}