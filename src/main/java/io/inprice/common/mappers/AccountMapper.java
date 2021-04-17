package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.AccountStatus;
import io.inprice.common.models.Account;

public class AccountMapper implements RowMapper<Account> {

  @Override
  public Account map(ResultSet rs, StatementContext ctx) throws SQLException {
    Account m = new Account();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "link_limit")) m.setLinkLimit(Helper.nullIntegerHandler(rs, "link_limit"));
    if (Helper.hasColumn(rs, "link_count")) m.setLinkCount(Helper.nullIntegerHandler(rs, "link_count"));
    if (Helper.hasColumn(rs, "last_status_update")) m.setLastStatusUpdate(rs.getTimestamp("last_status_update"));
    if (Helper.hasColumn(rs, "cust_id")) m.setCustId(rs.getString("cust_id"));
    if (Helper.hasColumn(rs, "subs_id")) m.setSubsId(rs.getString("subs_id"));
    if (Helper.hasColumn(rs, "subs_started_at")) m.setSubsStartedAt(rs.getTimestamp("subs_started_at"));
    if (Helper.hasColumn(rs, "renewal_at")) m.setRenewalAt(rs.getTimestamp("renewal_at"));
    if (Helper.hasColumn(rs, "title")) m.setTitle(rs.getString("title"));
    if (Helper.hasColumn(rs, "address_1")) m.setAddress1(rs.getString("address_1"));
    if (Helper.hasColumn(rs, "address_2")) m.setAddress2(rs.getString("address_2"));
    if (Helper.hasColumn(rs, "postcode")) m.setPostcode(rs.getString("postcode"));
    if (Helper.hasColumn(rs, "city")) m.setCity(rs.getString("city"));
    if (Helper.hasColumn(rs, "state")) m.setState(rs.getString("state"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));
    if (Helper.hasColumn(rs, "currency_code")) m.setCurrencyCode(rs.getString("currency_code"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "admin_id")) m.setAdminId(rs.getLong("admin_id"));
    if (Helper.hasColumn(rs, "demo")) m.setDemo(rs.getBoolean("demo"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(AccountStatus.valueOf(status));
    }

    return m;
  }

}