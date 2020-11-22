package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.SubsStatus;
import io.inprice.common.models.Company;

public class CompanyMapper implements RowMapper<Company> {

  @Override
  public Company map(ResultSet rs, StatementContext ctx) throws SQLException {
    Company m = new Company();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "currency_code")) m.setCurrencyCode(rs.getString("currency_code"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "product_limit")) m.setProductLimit(Helper.nullIntegerHandler(rs, "product_limit"));
    if (Helper.hasColumn(rs, "product_count")) m.setProductCount(Helper.nullIntegerHandler(rs, "product_count"));
    if (Helper.hasColumn(rs, "admin_id")) m.setAdminId(rs.getLong("admin_id"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "title")) m.setTitle(rs.getString("title"));
    if (Helper.hasColumn(rs, "address_1")) m.setAddress1(rs.getString("address_1"));
    if (Helper.hasColumn(rs, "address_2")) m.setAddress2(rs.getString("address_2"));
    if (Helper.hasColumn(rs, "postcode")) m.setPostcode(rs.getString("postcode"));
    if (Helper.hasColumn(rs, "city")) m.setCity(rs.getString("city"));
    if (Helper.hasColumn(rs, "state")) m.setState(rs.getString("state"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));
    if (Helper.hasColumn(rs, "free_usage")) m.setFreeUsage(rs.getBoolean("free_usage"));
    if (Helper.hasColumn(rs, "subs_id")) m.setSubsId(rs.getString("subs_id"));
    if (Helper.hasColumn(rs, "subs_customer_id")) m.setSubsCustomerId(rs.getString("subs_customer_id"));
    if (Helper.hasColumn(rs, "subs_renewal_at")) m.setSubsRenewalAt(rs.getTimestamp("subs_renewal_at"));

    if (Helper.hasColumn(rs, "subs_status")) {
      String subsStatus = rs.getString("subs_status");
      if (subsStatus != null) m.setSubsStatus(SubsStatus.valueOf(subsStatus));
    }

    return m;
  }

}