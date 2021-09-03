package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 7276097303096516212L;

	private Long id;

  @JsonIgnore
  private Long accountId;

  private Date createdAt;
	private Integer createdYear;
  private String createdMonth;

}
