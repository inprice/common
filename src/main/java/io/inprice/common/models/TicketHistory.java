package io.inprice.common.models;

import io.inprice.common.meta.TicketPriority;
import io.inprice.common.meta.TicketStatus;
import io.inprice.common.meta.TicketSubject;
import io.inprice.common.meta.TicketType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketHistory extends BaseModel {

	private static final long serialVersionUID = -5309599393907112703L;

	private Long ticketId;
	private TicketStatus status;
	private TicketPriority priority;
	private TicketType type;
	private TicketSubject subject;
  private Long userId;
  
  //transients
  private String username;

}
