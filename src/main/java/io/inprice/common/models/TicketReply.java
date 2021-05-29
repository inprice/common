package io.inprice.common.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketReply extends BaseModel {

	private static final long serialVersionUID = -5309599393907112703L;

  private Long ticketId;
  private Boolean editable = Boolean.FALSE;
  private String reply;
  private Boolean fromUser;
  private Long userId;

}
