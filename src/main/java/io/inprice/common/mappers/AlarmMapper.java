package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.AlarmSubject;
import io.inprice.common.meta.AlarmSubjectWhen;
import io.inprice.common.meta.AlarmTopic;
import io.inprice.common.meta.Position;
import io.inprice.common.models.Alarm;

public class AlarmMapper implements RowMapper<Alarm> {

  @Override
  public Alarm map(ResultSet rs, StatementContext ctx) throws SQLException {
  	Alarm m = new Alarm();

		if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
		if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));

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
		
		if (Helper.hasColumn(rs, "certain_position")) m.setCertainPosition(rs.getString("certain_position"));
    if (Helper.hasColumn(rs, "amount_lower_limit")) m.setAmountLowerLimit(rs.getBigDecimal("amount_lower_limit"));
    if (Helper.hasColumn(rs, "amount_upper_limit")) m.setAmountUpperLimit(rs.getBigDecimal("amount_upper_limit"));

    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "workspace_id")) m.setWorkspaceId(rs.getLong("workspace_id"));

    //transients
		if (Helper.hasColumn(rs, "entity_id")) m.setEntityId(rs.getLong("entity_id"));
  	if (Helper.hasColumn(rs, "entity_sku")) m.setEntitySku(rs.getString("entity_sku"));
  	if (Helper.hasColumn(rs, "entity_name")) m.setEntityName(rs.getString("entity_name"));

  	if (Helper.hasColumn(rs, "entity_position")) {
    	String val = rs.getString("entity_position");
    	if (val != null) m.setEntityPosition(Position.valueOf(val));
    }

  	if (Helper.hasColumn(rs, "entity_price")) m.setEntityPrice(rs.getBigDecimal("entity_price"));
  	if (Helper.hasColumn(rs, "entity_min_price")) m.setEntityMinPrice(rs.getBigDecimal("entity_min_price"));
  	if (Helper.hasColumn(rs, "entity_avg_price")) m.setEntityAvgPrice(rs.getBigDecimal("entity_avg_price"));
  	if (Helper.hasColumn(rs, "entity_max_price")) m.setEntityMaxPrice(rs.getBigDecimal("entity_max_price"));

  	if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
  	if (Helper.hasColumn(rs, "full_name")) m.setFullName(rs.getString("full_name"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));

  	return m;
  }

}