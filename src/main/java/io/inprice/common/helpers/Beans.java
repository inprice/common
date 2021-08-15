package io.inprice.common.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Beans {

   private static final Logger logger = LoggerFactory.getLogger(Beans.class);

   private static Map<Class<?>, Object> singletonMap = new HashMap<>();

   @SuppressWarnings({ "unchecked", "rawtypes" })
   public synchronized static <T> T getSingleton(Class<?> clazz) {
      T obj = (T) singletonMap.get(clazz);

      if (obj == null) {
         try {
            Constructor con = clazz.getDeclaredConstructor();
            con.setAccessible(true);
            obj = (T) con.newInstance();
         } catch (Exception e) {
            logger.error("Error", e);
         }
         singletonMap.put(clazz, obj);
      }
      return obj;
   }

}
