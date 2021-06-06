package io.inprice.common.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketComment extends BaseModel {

	private static final long serialVersionUID = -5309599393907112703L;

  private Long ticketId;
  private Boolean editable = Boolean.FALSE;
  private Boolean addedByUser = Boolean.TRUE;
  private String body;
  private Long userId;

  //transients
  private String username;

}
