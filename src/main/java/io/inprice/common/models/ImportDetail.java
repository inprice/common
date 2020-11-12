package io.inprice.common.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportDetail {
  
  private Long id;
  private String data;
  private Boolean eligible;
  private Boolean imported;
  private String problem;
  private Date lastCheck;
  private Long importId;

  @JsonIgnore
  private Long companyId;

}
