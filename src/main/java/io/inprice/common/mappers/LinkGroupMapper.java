package io.inprice.common.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.inprice.common.meta.Level;
import io.inprice.common.models.LinkGroup;

public class LinkGroupMapper implements RowMapper<LinkGroup> {

  @Override
  public LinkGroup map(ResultSet rs, StatementContext ctx) throws SQLException {
    LinkGroup m = new LinkGroup();
    Helper.mapBaseFields(m, rs);

    if (Helper.hasColumn(rs, "name")) m.setName(rs.getString("name"));
    if (Helper.hasColumn(rs, "total")) m.setTotal(rs.getBigDecimal("total"));

    if (Helper.hasColumn(rs, "actives")) m.setActives(rs.getInt("actives"));
    if (Helper.hasColumn(rs, "waitings")) m.setWaitings(rs.getInt("waitings"));
    if (Helper.hasColumn(rs, "tryings")) m.setTryings(rs.getInt("tryings"));
    if (Helper.hasColumn(rs, "problems")) m.setProblems(rs.getInt("problems"));

    if (Helper.hasColumn(rs, "price")) m.setPrice(rs.getBigDecimal("price"));
    if (Helper.hasColumn(rs, "min_diff")) m.setMinDiff(rs.getBigDecimal("min_diff"));
    if (Helper.hasColumn(rs, "avg_diff")) m.setAvgDiff(rs.getBigDecimal("avg_diff"));
    if (Helper.hasColumn(rs, "max_diff")) m.setMaxDiff(rs.getBigDecimal("max_diff"));

    if (Helper.hasColumn(rs, "min_platform")) m.setMinPlatform(rs.getString("min_platform"));
    if (Helper.hasColumn(rs, "min_seller")) m.setMinSeller(rs.getString("min_seller"));
    if (Helper.hasColumn(rs, "min_price")) m.setMinPrice(rs.getBigDecimal("min_price"));
    if (Helper.hasColumn(rs, "avg_price")) m.setAvgPrice(rs.getBigDecimal("avg_price"));
    if (Helper.hasColumn(rs, "max_platform")) m.setMaxPlatform(rs.getString("max_platform"));
    if (Helper.hasColumn(rs, "max_seller")) m.setMaxSeller(rs.getString("max_seller"));
    if (Helper.hasColumn(rs, "max_price")) m.setMaxPrice(rs.getBigDecimal("max_price"));
    
    if (Helper.hasColumn(rs, "updated_at")) m.setUpdatedAt(rs.getTimestamp("updated_at"));

    if (Helper.hasColumn(rs, "level")) {
      String val = rs.getString("level");
      if (val != null) m.setLevel(Level.valueOf(val));
    }

    return m;
  }

}