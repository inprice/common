package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.AlarmSubject;
import io.inprice.common.meta.AlarmSubjectWhen;
import io.inprice.common.meta.AlarmTopic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Alarm implements Serializable {

	private static final long serialVersionUID = -7971852075540080006L;

	private Long id;
	
  private AlarmTopic topic;
	private AlarmSubject subject;
	private AlarmSubjectWhen subjectWhen;

	private String certainStatus;
	private BigDecimal amountLowerLimit;
	private BigDecimal amountUpperLimit;

	private String lastStatus;
	private BigDecimal lastAmount;
	
	private Boolean tobeNotified;
	private Date notifiedAt;
  private Date updatedAt;
	
  private Long linkId;
  private Long productId;

  @JsonIgnore
  private Long accountId;
  
  //transients
  private String name;
  private String linkUrl;

  private String email;
  private String username;
  private String currencyFormat;

}
