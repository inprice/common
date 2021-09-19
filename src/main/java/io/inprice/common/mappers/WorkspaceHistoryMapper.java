package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.WorkspaceStatus;
import io.inprice.common.models.WorkspaceHistory;

public class WorkspaceHistoryMapper implements RowMapper<WorkspaceHistory> {

  @Override
  public WorkspaceHistory map(ResultSet rs, StatementContext ctx) throws SQLException {
    WorkspaceHistory m = new WorkspaceHistory();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "plan_id")) m.setPlanId(rs.getInt("plan_id"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(WorkspaceStatus.valueOf(status));
    }

    return m;
  }

}