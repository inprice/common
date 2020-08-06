package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

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
  private String passwordHash;
  private String passwordSalt;
  private String stripeCustId;
  private Date createdAt;

  @Override
  public String toString() {
    return "[createdAt=" + createdAt + ", email=" + email + ", id=" + id + ", name=" + name + ", timezone="
        + timezone + "]";
  }

}
