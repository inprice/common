package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.AccountStatus;
import io.inprice.common.meta.UserRole;
import io.inprice.common.meta.UserStatus;
import io.inprice.common.models.Member;

public class MemberMapper implements RowMapper<Member> {

  @Override
  public Member map(ResultSet rs, StatementContext ctx) throws SQLException {
    Member m = new Member();

    if (Helper.hasColumn(rs, "id")) m.setId(rs.getLong("id"));
    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "user_id")) m.setUserId(Helper.nullLongHandler(rs, "user_id"));
    if (Helper.hasColumn(rs, "role")) m.setRole(UserRole.valueOf(rs.getString("role")));
    if (Helper.hasColumn(rs, "status")) m.setStatus(UserStatus.valueOf(rs.getString("status")));
    if (Helper.hasColumn(rs, "pre_status")) m.setPreStatus(UserStatus.valueOf(rs.getString("pre_status")));
    if (Helper.hasColumn(rs, "retry")) m.setRetry(Helper.nullIntegerHandler(rs, "retry"));
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));
    if (Helper.hasColumn(rs, "account_id")) m.setAccountId(rs.getLong("account_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    //transients
    if (Helper.hasColumn(rs, "account_name")) m.setAccountName(rs.getString("account_name"));
    if (Helper.hasColumn(rs, "plan_id")) m.setPlanId(rs.getInt("plan_id"));
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "user_limit")) m.setUserLimit(rs.getInt("user_limit"));
    if (Helper.hasColumn(rs, "user_count")) m.setUserCount(rs.getInt("user_count"));
    if (Helper.hasColumn(rs, "link_limit")) m.setLinkLimit(rs.getInt("link_limit"));
    if (Helper.hasColumn(rs, "link_count")) m.setLinkCount(rs.getInt("link_count"));
    if (Helper.hasColumn(rs, "alarm_limit")) m.setAlarmLimit(rs.getInt("alarm_limit"));
    if (Helper.hasColumn(rs, "alarm_count")) m.setAlarmCount(rs.getInt("alarm_count"));
    if (Helper.hasColumn(rs, "subs_started_at")) m.setSubsStartedAt(rs.getTimestamp("subs_started_at"));
    if (Helper.hasColumn(rs, "subs_renewal_at")) m.setSubsRenewalAt(rs.getDate("subs_renewal_at"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "last_status_update")) m.setLastStatusUpdate(rs.getTimestamp("last_status_update")); //for account

    if (Helper.hasColumn(rs, "account_status")) {
      String val = rs.getString("account_status");
      if (val != null) m.setAccountStatus(AccountStatus.valueOf(val));
    }

    return m;
  }

}