package io.inprice.common.models;

import java.io.Serializable;
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
public class Ticket implements Serializable {

	private static final long serialVersionUID = -5309599393907112703L;

	private Integer id;
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
  private Date createdAt = new Date();

  @JsonIgnore
  private Long userId;

  @JsonIgnore
  private Long accountId;

}
