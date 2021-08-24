package io.inprice.common.helpers;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JsonConverter {

  private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

  public static final ObjectMapper mapper;
  private static final ObjectMapper mapperWithoutJsonIgnore;

  static {
    mapper = 
      new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        .setTimeZone(TimeZone.getDefault());

    mapperWithoutJsonIgnore = 
      new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setSerializationInclusion(Include.NON_NULL)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        .setTimeZone(TimeZone.getDefault())
        .setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
					private static final long serialVersionUID = 1L;
					@Override
          protected <A extends Annotation> A _findAnnotation(Annotated annotated, Class<A> annoClass) {
            if (annoClass == JsonIgnore.class) {
              return null;
            }
            return super._findAnnotation(annotated, annoClass);
          }
        });
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

  public static <T> T fromJsonWithoutJsonIgnore(String value, Class<T> clazz) {
    try {
      return mapperWithoutJsonIgnore.readValue(value, clazz);
    } catch (Exception e) {
      logger.error("Failed to parse a json (w/o JsonIgnore)", e);
    }
    return null;
  }

  public static <T> String toJsonWithoutJsonIgnore(T value) {
    try {
      return mapperWithoutJsonIgnore.writeValueAsString(value);
    } catch (Exception e) {
      logger.error("Failed to convert a json to string (w/o JsonIgnore)", e);
    }
    return null;
  }

}