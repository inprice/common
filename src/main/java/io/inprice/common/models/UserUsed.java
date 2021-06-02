package io.inprice.common.models;

import io.inprice.common.meta.PermType;
import lombok.Getter;
import lombok.Setter;

/**
 * Saves email of users' who has already used some certain type of permission like free use!
 * 
 * @author mdpinar
 *
 */
@Getter
@Setter
public class UserUsed extends BaseModel {

	private static final long serialVersionUID = 4566418289281174593L;

	private String email;
  private PermType permType;
  private Boolean whitelisted = Boolean.FALSE; //for allowing the same user to be able to use the same permission multiple times!

}
