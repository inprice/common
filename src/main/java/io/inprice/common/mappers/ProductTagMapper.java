package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.models.ProductTag;

public class ProductTagMapper implements RowMapper<ProductTag> {

  @Override
  public ProductTag map(ResultSet rs, StatementContext ctx) throws SQLException {
    ProductTag m = new ProductTag();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "product_id")) m.setProductId(rs.getLong("product_id"));
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));

    return m;
  }

}