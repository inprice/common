package io.inprice.common.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.TicketCSatLevel;
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

	private TicketType type;
	private TicketSubject subject;
  private String query;
  private String reply;
  private Long linkId;
  private Long groupId;
  private TicketCSatLevel csatLevel;
  private String csatAssessment; //is mandatory for ENOUGH, NEUTRAL and BAD levels!
  private Date csatedAt;
  private Date repliedAt;

  @JsonIgnore
  private Long userId;

}
