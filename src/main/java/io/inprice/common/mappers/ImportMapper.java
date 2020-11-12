package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.ImportType;
import io.inprice.common.models.Import;

public class ImportMapper implements RowMapper<Import> {

  @Override
  public Import map(ResultSet rs, StatementContext ctx) throws SQLException {
    Import m = new Import();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "success_count")) m.setSuccessCount(rs.getInt("success_count"));
    if (Helper.hasColumn(rs, "problem_count")) m.setProblemCount(rs.getInt("problem_count"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "type")) {
      String type = rs.getString("type");
      if (type != null) m.setType(ImportType.valueOf(type));
    }

    return m;
  }

}