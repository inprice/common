package io.inprice.scrapper.common.helpers;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

  public static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
  }

  public static <T> T fromJson(String value, Class<T> clazz) {
    try {
      return mapper.readValue(value, clazz);
    } catch (JsonProcessingException ignored) { }
    return null;
  }

  public static <T> String toJson(T value) {
     try {
        return mapper.writeValueAsString(value);
     } catch (JsonProcessingException ignored) { }
     return null;
  }

}