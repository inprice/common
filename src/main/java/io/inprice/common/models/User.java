package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

  private static final long serialVersionUID = 1753526228909974777L;

  private Long id;
  private String email;
  private String name;
  private String timezone;
  private boolean privileged;
  private Date createdAt;

  @JsonIgnore
  private String password;

  private Long accountId; //set only when super user switches account!

}
