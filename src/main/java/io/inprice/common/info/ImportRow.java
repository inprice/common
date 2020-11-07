package io.inprice.common.info;

import java.util.Date;

import io.inprice.common.meta.ImportType;
import io.inprice.common.meta.LinkStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportRow {
  
  private Long id; // link id
  private String data;
  private Date lastUpdate;
  private Date lastCheck;
  private LinkStatus status = LinkStatus.TOBE_CLASSIFIED;
  private String problem;
  private Integer retry;
  private Integer httpStatus;
  private ImportType importType;

}
