package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.CheckoutStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Checkout implements Serializable {

  private static final long serialVersionUID = -2323544177591811430L;

  @JsonIgnore
  private String hash;

  @JsonIgnore
  private String sessionId; //from stripe

  @JsonIgnore
  private Long companyId;
  private Integer planId;
  private CheckoutStatus status;
  private Date updatedAt;
  private Date createdAt;

}
