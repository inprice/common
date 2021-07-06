package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.UserMarkType;
import io.inprice.common.models.UserMark;

public class UserMarkMapper implements RowMapper<UserMark> {

  @Override
  public UserMark map(ResultSet rs, StatementContext ctx) throws SQLException {
    UserMark m = new UserMark();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "email")) m.setEmail(rs.getString("email"));
    if (Helper.hasColumn(rs, "whitelisted")) m.setWhitelisted(rs.getBoolean("whitelisted"));
    if (Helper.hasColumn(rs, "description")) m.setDescription(rs.getString("description"));

    if (Helper.hasColumn(rs, "type")) {
      String val = rs.getString("type");
      if (StringUtils.isNotBlank(val)) m.setType(UserMarkType.valueOf(val));
    }

    return m;
  }

}