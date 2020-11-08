package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.inprice.common.info.ImportRow;
import io.inprice.common.meta.ImportType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Import implements Serializable {

  private static final long serialVersionUID = 7780231473342897573L;

  private Long id;
  private ImportType type;
  private Long companyId;
  private Date createdAt = new Date();

  //transients
  private List<ImportRow> list;

}
