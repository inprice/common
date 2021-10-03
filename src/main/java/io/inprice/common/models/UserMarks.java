package io.inprice.common.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Tracks users by their emails to check if they have already used some certain type of permission like free use!
 * 
 * @author mdpinar
 */
@Getter
@Setter
public class UserMarks extends BaseModel {

	private static final long serialVersionUID = 4566418289281174593L;

	private String email;
  private String mark;
  private Boolean booleanVal;
  private String stringVal;
  private Integer integerVal;
  private Date dateVal;

}
