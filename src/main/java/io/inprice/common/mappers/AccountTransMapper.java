package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.SubsEvent;
import io.inprice.common.models.AccountTrans;

public class AccountTransMapper implements RowMapper<AccountTrans> {

  @Override
  public AccountTrans map(ResultSet rs, StatementContext ctx) throws SQLException {
    AccountTrans m = new AccountTrans();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "event_id")) m.setEventId(rs.getString("event_id"));
    if (Helper.hasColumn(rs, "successful")) m.setSuccessful(rs.getBoolean("successful"));
    if (Helper.hasColumn(rs, "reason")) m.setReason(rs.getString("reason"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));
    if (Helper.hasColumn(rs, "file_url")) m.setFileUrl(rs.getString("file_url"));

    if (Helper.hasColumn(rs, "event")) {
      String event = rs.getString("event");
      if (event != null) m.setEvent(SubsEvent.findByDescription(event));
    }

    return m;
  }

}