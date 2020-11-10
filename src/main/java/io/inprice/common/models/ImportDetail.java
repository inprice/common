package io.inprice.common.models;

import java.util.Date;

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
  private Long companyId;

}
