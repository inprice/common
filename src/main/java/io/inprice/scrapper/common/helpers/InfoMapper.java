package io.inprice.scrapper.common.helpers;

import java.sql.ResultSet;

public interface InfoMapper<M> {

    M map(ResultSet rs);

}
