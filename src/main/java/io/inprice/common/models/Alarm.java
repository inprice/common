package io.inprice.common.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.AlarmSubject;
import io.inprice.common.meta.AlarmSubjectWhen;
import io.inprice.common.meta.AlarmTopic;
import io.inprice.common.meta.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alarm implements Serializable {

	private static final long serialVersionUID = -7971852075540080006L;

	private Long id;
	private String name;

  private AlarmTopic topic;
	private AlarmSubject subject;
	private AlarmSubjectWhen subjectWhen;

	private String certainPosition;
	private BigDecimal amountLowerLimit;
	private BigDecimal amountUpperLimit;

	private Date updatedAt;
  private Date createdAt;

  @JsonIgnore
  private Long workspaceId;
  
  //transients
  private Long entityId; //link or product
  private String entitySku;
  private String entityName;
  private Position entityPosition = Position.NotSet;
  private BigDecimal entityPrice;
  private BigDecimal entityMinPrice;
  private BigDecimal entityAvgPrice;
  private BigDecimal entityMaxPrice;

  private String email;
  private String fullName;
  private String currencyFormat;

}
