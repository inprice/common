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
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "parse_problem")) m.setParseProblem(rs.getString("parse_problem"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(LinkStatus.valueOf(status));
    }

    return m;
  }

}