package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.CompanyStatus;
import io.inprice.common.models.CompanyHistory;

public class CompanyHistoryMapper implements RowMapper<CompanyHistory> {

  @Override
  public CompanyHistory map(ResultSet rs, StatementContext ctx) throws SQLException {
    CompanyHistory m = new CompanyHistory();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(CompanyStatus.valueOf(status));
    }

    return m;
  }

}