package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.LinkStatus;
import io.inprice.common.models.LinkHistory;

public class LinkHistoryMapper implements RowMapper<LinkHistory> {

  @Override
  public LinkHistory map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkHistory m = new LinkHistory();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "http_status")) m.setHttpStatus(Helper.nullIntegerHandler(rs, "http_status"));
    if (Helper.hasColumn(rs, "problem")) m.setProblem(rs.getString("problem"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(LinkStatus.valueOf(status));
    }

    return m;
  }

}