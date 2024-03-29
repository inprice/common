package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.LinkSpec;

public class LinkSpecMapper implements RowMapper<LinkSpec> {

  @Override
  public LinkSpec map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkSpec m = new LinkSpec();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "link_id")) m.setLinkId(rs.getLong("link_id"));
    if (Helper.hasColumn(rs, "_key")) m.setKey(rs.getString("_key"));
    if (Helper.hasColumn(rs, "_value")) m.setValue(rs.getString("_value"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));

    return m;
  }

}