package io.inprice.scrapper.common.helpers;

import io.inprice.scrapper.common.models.Model;

import java.sql.ResultSet;

public interface ModelMapper<M extends Model> {

    M map(ResultSet rs);

}
