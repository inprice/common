package io.inprice.common.models;

import java.util.Date;
import java.util.List;

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
public class Ticket extends BaseModel {

	private static final long serialVersionUID = -5309599393907112703L;

	private TicketStatus status;
	private TicketPriority priority;
	private TicketType type;
	private TicketSubject subject;
  private String issue;
  private Date progressedAt;
  private Long userId;

  //transients
  private String email;
  private List<TicketComment> commentList;

}
