package io.inprice.common.helpers;

import java.sql.ResultSet;

/**
 * RepositoryHelper
 */
public class RepositoryHelper {

   /**
    * JDBC return 0 when long field is null!!!
    * with this method we can return null it.
   */
   public static Long nullLongHandler(ResultSet rs, String field) {
      try {
         Long val = rs.getLong(field);
         if (! rs.wasNull()) return val;
      } catch (Exception ignored) { }
      return null;
   }

   public static Integer nullIntegerHandler(ResultSet rs, String field) {
      try {
         Integer val = rs.getInt(field);
         if (! rs.wasNull()) return val;
      } catch (Exception ignored) { }
      return null;
   }

}