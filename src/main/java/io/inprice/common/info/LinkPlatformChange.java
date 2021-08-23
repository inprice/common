package io.inprice.common.info;

import java.io.Serializable;

import io.inprice.common.meta.LinkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author mdpinar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkPlatformChange implements Serializable {

	private static final long serialVersionUID = 8846477959388296254L;

	private Long linkId;
  private Long newPlatformId;
  private LinkStatus status;

}
