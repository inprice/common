package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.inprice.common.meta.AlarmSubject;
import io.inprice.common.meta.AlarmSubjectWhen;
import io.inprice.common.meta.AlarmTopic;
import io.inprice.common.models.Alarm;
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
  
  public static Alarm mapForAlarm(ResultSet rs) throws SQLException {
  	return mapForAlarm(rs, null, null, null, null);
  }

  public static Alarm mapForAlarm(ResultSet rs, Long alarmId, Long linkId, Long productId, Long workspaceId) throws SQLException {
    Alarm m = new Alarm();
    
    String prefix = "";
    
    if (alarmId != null) {
    	prefix = "al_";
  		m.setId(alarmId);
  		m.setLinkId(linkId);
  		m.setProductId(productId);
  		m.setWorkspaceId(workspaceId);
    } else {
      if (Helper.hasColumn(rs, "link_id")) m.setLinkId(Helper.nullLongHandler(rs, "link_id"));
      if (Helper.hasColumn(rs, "product_id")) m.setProductId(Helper.nullLongHandler(rs, "product_id"));
      if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));
    }
    
		if (Helper.hasColumn(rs, prefix+"id")) m.setId(rs.getLong(prefix+"id"));
    if (Helper.hasColumn(rs, "certain_status")) m.setCertainStatus(rs.getString("certain_status"));
    if (Helper.hasColumn(rs, "amount_lower_limit")) m.setAmountLowerLimit(rs.getBigDecimal("amount_lower_limit"));
    if (Helper.hasColumn(rs, "amount_upper_limit")) m.setAmountUpperLimit(rs.getBigDecimal("amount_upper_limit"));

    if (Helper.hasColumn(rs, "last_status")) m.setLastStatus(rs.getString("last_status"));
    if (Helper.hasColumn(rs, "last_amount")) m.setLastAmount(rs.getBigDecimal("last_amount"));

    if (Helper.hasColumn(rs, "tobe_notified")) m.setTobeNotified(rs.getBoolean("tobe_notified"));
    if (Helper.hasColumn(rs, "notified_at")) m.setNotifiedAt(rs.getTimestamp("notified_at"));

    if (Helper.hasColumn(rs, "updated_at")) {
    	m.setUpdatedAt(rs.getTimestamp("updated_at"));
    } else if (Helper.hasColumn(rs, "al_updated_at")) {
    	m.setUpdatedAt(rs.getTimestamp("al_updated_at"));
    }
    
    if (Helper.hasColumn(rs, "topic")) {
    	String val = rs.getString("topic");
    	if (val != null) m.setTopic(AlarmTopic.valueOf(val));
    }

    if (Helper.hasColumn(rs, "subject")) {
    	String val = rs.getString("subject");
    	if (val != null) m.setSubject(AlarmSubject.valueOf(val));
    }
		if (Helper.hasColumn(rs, "subject_when")) {
			String val = rs.getString("subject_when");
			if (val != null) m.setSubjectWhen(AlarmSubjectWhen.valueOf(val));
		}
		
    return m;
  }

}