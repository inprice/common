package io.inprice.common.helpers;

import java.sql.ResultSet;

public interface ModelMapper<M> {

   M map(ResultSet rs);

}
