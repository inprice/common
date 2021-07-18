package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.AccountStatus;
import io.inprice.common.models.Account;
import io.inprice.common.models.Plan;

public class AccountMapper implements RowMapper<Account> {

  @Override
  public Account map(ResultSet rs, StatementContext ctx) throws SQLException {
    Account m = new Account();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "title")) m.setTitle(rs.getString("title"));
    if (Helper.hasColumn(rs, "contact_name")) m.setContactName(rs.getString("contact_name"));
    if (Helper.hasColumn(rs, "address_1")) m.setAddress1(rs.getString("address_1"));
    if (Helper.hasColumn(rs, "address_2")) m.setAddress2(rs.getString("address_2"));
    if (Helper.hasColumn(rs, "postcode")) m.setPostcode(rs.getString("postcode"));
    if (Helper.hasColumn(rs, "city")) m.setCity(rs.getString("city"));
    if (Helper.hasColumn(rs, "state")) m.setState(rs.getString("state"));
    if (Helper.hasColumn(rs, "country")) m.setCountry(rs.getString("country"));

    if (Helper.hasColumn(rs, "user_count")) m.setUserCount(Helper.nullIntegerHandler(rs, "user_count"));
    if (Helper.hasColumn(rs, "link_count")) m.setLinkCount(Helper.nullIntegerHandler(rs, "link_count"));
    if (Helper.hasColumn(rs, "alarm_count")) m.setAlarmCount(Helper.nullIntegerHandler(rs, "alarm_count"));

    if (Helper.hasColumn(rs, "subs_started_at")) m.setSubsStartedAt(rs.getTimestamp("subs_started_at"));
    if (Helper.hasColumn(rs, "subs_renewal_at")) m.setSubsRenewalAt(rs.getTimestamp("subs_renewal_at"));

    if (Helper.hasColumn(rs, "currency_code")) m.setCurrencyCode(rs.getString("currency_code"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "demo")) m.setDemo(rs.getBoolean("demo"));
    if (Helper.hasColumn(rs, "admin_id")) m.setAdminId(rs.getLong("admin_id"));

    if (Helper.hasColumn(rs, "pre_status")) m.setPreStatus(rs.getString("pre_status"));

    if (Helper.hasColumn(rs, "last_status_update")) m.setLastStatusUpdate(rs.getTimestamp("last_status_update"));
    if (Helper.hasColumn(rs, "status")) {
      String status = rs.getString("status");
      if (status != null) m.setStatus(AccountStatus.valueOf(status));
    }

    if (Helper.hasColumn(rs, "plan_id")) m.setPlanId(Helper.nullIntegerHandler(rs, "plan_id"));

    //transients
    if (m.getPlanId() != null) {
      Plan plan = new Plan();
    	plan.setId(m.getPlanId());
    	if (Helper.hasColumn(rs, "plan_name")) plan.setName(rs.getString("plan_name"));
    	if (Helper.hasColumn(rs, "user_limit")) plan.setUserLimit(rs.getInt("user_limit"));
    	if (Helper.hasColumn(rs, "link_limit")) plan.setLinkLimit(rs.getInt("link_limit"));
    	if (Helper.hasColumn(rs, "alarm_limit")) plan.setAlarmLimit(rs.getInt("alarm_limit"));
      m.setPlan(plan);
    }

    if (Helper.hasColumn(rs, "xid")) m.setXid(rs.getLong("xid"));
    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));

    return m;
  }

}