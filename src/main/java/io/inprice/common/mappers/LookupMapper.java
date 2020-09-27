package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.LookupType;
import io.inprice.common.models.Lookup;

public class LookupMapper implements RowMapper<Lookup> {

  @Override
  public Lookup map(ResultSet rs, StatementContext ctx) throws SQLException {
    Lookup m = new Lookup();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    
    if (Helper.hasColumn(rs, "type")) {
      String type = rs.getString("type");
      if (type != null) m.setType(LookupType.valueOf(type));
    }

    return m;
  }

}