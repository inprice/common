package io.inprice.common.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.meta.SubsEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTrans implements Serializable {

  private static final long serialVersionUID = -4787008755878198572L;

  private Long id;
  private String eventId;  // evt_1HBOzGBiHTcqawyMeOM8iaYx
  private SubsEvent event;    // invoice.payment_succeeded
  private Boolean successful = Boolean.FALSE;
  private String reason;   // subscription_create
  private String description; // 1 × Basic Plan (at $10.00 / month)
  private String fileUrl;
  private Date createdAt;

  @JsonIgnore
  private Long accountId;  // 1

}
