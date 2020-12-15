package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.CompanyStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyHistory implements Serializable {

  private static final long serialVersionUID = -323250016123276994L;

  @JsonIgnore
  private Long id;
  private Long companyId;
  private CompanyStatus status;
  private String planName;
  private String custId;
  private String subsId;
  private Date createdAt = new Date();

}
