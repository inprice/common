package io.inprice.common.models;

import io.inprice.common.meta.SubsEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTrans extends BaseModel {

  private static final long serialVersionUID = -4787008755878198572L;

  private String eventId;
  private SubsEvent event;
  private Boolean successful = Boolean.FALSE;
  private String reason;
  private String description;
  private String fileUrl;

}
