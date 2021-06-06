package io.inprice.common.models;

import java.util.Date;

import io.inprice.common.meta.AnnounceLevel;
import io.inprice.common.meta.AnnounceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Announce extends BaseModel {

	private static final long serialVersionUID = -4242093738319105109L;

	private AnnounceType type;
	private AnnounceLevel level;
	private String title;
	private String body;
  private String link;
  private Boolean active;
  private Date startingAt;
  private Date endingAt;
  private Long userId;

}
