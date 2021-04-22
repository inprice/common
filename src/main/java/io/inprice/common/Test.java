package io.inprice.common;

import org.jdbi.v3.core.Handle;

import io.inprice.common.converters.GroupRefreshResultConverter;
import io.inprice.common.helpers.Database;
import io.inprice.common.info.GroupRefreshResult;
import io.inprice.common.repository.CommonDao;

public class Test {
  
  public static void main(String[] args) {
  	try (Handle handle = Database.getHandle()) {
      CommonDao commonDao = handle.attach(CommonDao.class);
      GroupRefreshResult grr = GroupRefreshResultConverter.convert(commonDao.refreshGroup(1L));

      /*
      
      OutParameters result = handle
          .createCall("call sp_refresh_group(:groupId, :minPrice, :avgPrice, :maxPrice, :total, :level)	") 
          .bind("groupId", 1) 
          .registerOutParameter("minPrice", Types.DOUBLE)
      		.registerOutParameter("avgPrice", Types.DOUBLE)
  				.registerOutParameter("maxPrice", Types.DOUBLE)
  				.registerOutParameter("total", Types.DOUBLE)
					.registerOutParameter("level", Types.VARCHAR)
          .invoke();      
      GroupRefreshResult grr = GroupRefreshResultConverter.convert(result);
      */
      
			System.out.println(" -- GRR for GROUP -- " + grr);
    }
  }

}