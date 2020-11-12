package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  private Integer successCount;
  private Integer problemCount;
  private Date createdAt = new Date();
  
  @JsonIgnore
  private Long companyId;

  //transients
  private List<ImportDetail> details;

}
