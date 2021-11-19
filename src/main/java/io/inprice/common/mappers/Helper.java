package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.inprice.common.models.BaseModel;

/**
 * Helper class for Mappers
 */
public class Helper {

	static void mapBaseFields(BaseModel m, ResultSet rs) throws SQLException {
		if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    if (Helper.hasColumn(rs, "created_year")) m.setCreatedYear(rs.getInt("created_year"));
    if (Helper.hasColumn(rs, "created_month")) m.setCreatedMonth(rs.getString("created_month"));
	}

  /**
   * JDBC return 0 when long field is null!!! with this method we can return null
   * it.
   */
  public static Long nullLongHandler(ResultSet rs, String field) {
    try {
      long val = rs.getLong(field);
      if (rs.wasNull() == false) return val;
    } catch (Exception ignored) { }
    return null;
  }

  public static Integer nullIntegerHandler(ResultSet rs, String field) {
    try {
      int val = rs.getInt(field);
      if (rs.wasNull() == false) return val;
    } catch (Exception ignored) { }
    return null;
  }

  public static boolean hasColumn(ResultSet rs, String column) {
    try {
      rs.findColumn(column);
    } catch (SQLException sqlex) { 
      return false;
    }
    return true;
  }

}