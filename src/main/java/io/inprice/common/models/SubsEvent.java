package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.common.meta.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubsEvent implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Long id;
  private Long companyId;
  private EventType eventType;
  private String eventId;
  private String event;
  private String data;
  private Date createdAt;

}
