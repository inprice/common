package io.inprice.common.helpers;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

  private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

  public static final ObjectMapper mapper;

  static {
    mapper = 
      new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        .setTimeZone(TimeZone.getDefault())
        .configure(Feature.AUTO_CLOSE_SOURCE, true);
  }

  public static <T> T fromJson(String value, Class<T> clazz) {
    try {
      return mapper.readValue(value, clazz);
    } catch (Exception e) {
      logger.error("Failed to parse a json", e);
    }
    return null;
  }

  public static <T> String toJson(T value) {
    try {
      return mapper.writeValueAsString(value);
    } catch (Exception e) {
      logger.error("Failed to convert a json to string", e);
    }
    return null;
  }

}