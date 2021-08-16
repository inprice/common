package io.inprice.common.models;

import java.util.Date;

import io.inprice.common.framework.Exclude;

import io.inprice.common.meta.CheckoutStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Checkout extends BaseModel {

  private static final long serialVersionUID = -2323544177591811430L;

  @Exclude
  private String hash; //id

  @Exclude
  private String sessionId; //from payment gw

  private Integer planId;
  private CheckoutStatus status;
  private String description;
  private Date updatedAt;

  @Override
  public final Long getId() {
  	return null;
  }
  
  @Override
  public void setId(Long id) {
  	;
  }

}
