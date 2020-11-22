package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

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
    if (Helper.hasColumn(rs, "company_id")) m.setCompanyId(rs.getLong("company_id"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    //transients
    if (Helper.hasColumn(rs, "company_name")) m.setCompanyName(rs.getString("company_name"));
    if (Helper.hasColumn(rs, "free_usage")) m.setFreeUsage(rs.getBoolean("free_usage")); else m.setFreeUsage(false);
    if (Helper.hasColumn(rs, "plan_name")) m.setPlanName(rs.getString("plan_name"));
    if (Helper.hasColumn(rs, "subs_status")) m.setSubsStatus(rs.getString("subs_status"));
    if (Helper.hasColumn(rs, "subs_renewal_at")) m.setSubsRenewalAt(rs.getDate("subs_renewal_at"));
    if (Helper.hasColumn(rs, "currency_format")) m.setCurrencyFormat(rs.getString("currency_format"));
    if (Helper.hasColumn(rs, "product_count")) m.setProductCount(rs.getInt("product_count"));

    return m;
  }

}