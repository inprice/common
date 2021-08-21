package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.common.framework.Exclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 7276097303096516212L;

	private Long id;

  @Exclude
  private Long accountId;

  private Date createdAt;
	private Integer createdYear;
  private String createdMonth;

}
