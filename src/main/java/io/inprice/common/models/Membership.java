package io.inprice.common.models;

import java.util.Date;

import io.inprice.common.meta.WorkspaceStatus;
import io.inprice.common.meta.UserRole;
import io.inprice.common.meta.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Membership extends BaseModel {

  private static final long serialVersionUID = -7793356216234713734L;

  private String email;
  private Long userId;
  private UserRole role;
  private UserStatus status = UserStatus.PENDING;
  private UserStatus preStatus = UserStatus.PENDING;
  private Integer retry = 1;
  private Date updatedAt;

  // transient
  private String workspaceName;
  private String contactName;
  private WorkspaceStatus workspaceStatus = WorkspaceStatus.CREATED;
  private Date lastStatusUpdate; //for workspace
  private Integer planId;
  private String planName;
  private Date subsRenewalAt;
  private Date subsStartedAt;
  private Integer userLimit;
  private Integer userCount;
  private Integer linkLimit;
  private Integer linkCount;
  private Integer alarmLimit;
  private Integer alarmCount;
  private String currencyCode;
  private String currencyFormat;

}
