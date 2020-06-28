package io.inprice.scrapper.common.models;

import java.io.Serializable;

import io.inprice.scrapper.common.meta.LookupType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lookup implements Serializable {

  private static final long serialVersionUID = 6886045588196107119L;

  private Long id;
  private LookupType type;
  private String name;
  private Long companyId;

}
