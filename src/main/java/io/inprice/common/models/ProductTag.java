package io.inprice.common.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTag implements Serializable {

  private static final long serialVersionUID = 6886045588196107119L;

  private Long id;
  private String name;
  private Long productId;

  @JsonIgnore
  private Long companyId;

}
