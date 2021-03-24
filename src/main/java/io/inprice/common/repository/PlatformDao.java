package io.inprice.common.repository;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import io.inprice.common.mappers.PlatformMapper;
import io.inprice.common.models.Platform;

public interface PlatformDao {

  @SqlQuery("select * from platform where domain in (<domainList>) order by domain desc limit 1")
  @UseRowMapper(PlatformMapper.class)
  Platform findByDomainList(@BindList("domainList") List<String> domainList);

}