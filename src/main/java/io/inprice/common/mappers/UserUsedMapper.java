package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.PermType;
import io.inprice.common.models.UserUsed;

public class UserUsedMapper implements RowMapper<UserUsed> {

  @Override
  public UserUsed map(ResultSet rs, StatementContext ctx) throws SQLException {
    UserUsed m = new UserUsed();

    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "created_at")) m.setCreatedAt(rs.getTimestamp("created_at"));

    if (Helper.hasColumn(rs, "perm_type")) {
      String val = rs.getString("perm_type");
      if (StringUtils.isNotBlank(val)) m.setPermType(PermType.valueOf(val));
    }

    return m;
  }

}