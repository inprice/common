package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.ImportDetail;

public class ImportDetailMapper implements RowMapper<ImportDetail> {

  @Override
  public ImportDetail map(ResultSet rs, StatementContext ctx) throws SQLException {
    ImportDetail m = new ImportDetail();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "data")) m.setData(rs.getString("data"));
    if (Helper.hasColumn(rs, "eligible")) m.setEligible(rs.getBoolean("eligible"));
    if (Helper.hasColumn(rs, "imported")) m.setImported(rs.getBoolean("imported"));
    if (Helper.hasColumn(rs, "status")) m.setStatus(rs.getString("status"));
    if (Helper.hasColumn(rs, "last_check")) m.setLastCheck(rs.getTimestamp("last_check"));
    if (Helper.hasColumn(rs, "import_id")) m.setImportId(rs.getLong("import_id"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));

    return m;
  }

}