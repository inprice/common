package io.inprice.scrapper.common.models;

import java.io.Serializable;
import java.util.Date;

import io.inprice.scrapper.common.info.ProductDTO;
import io.inprice.scrapper.common.meta.ImportType;
import io.inprice.scrapper.common.meta.CompetitorStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImportProduct implements Serializable {

  private static final long serialVersionUID = 1099808351397302194L;

  private Long id;
  private ImportType importType;
  private String data;
  private CompetitorStatus status = CompetitorStatus.TOBE_CLASSIFIED;
  private Date lastCheck;
  private Date lastUpdate;
  private Integer retry;
  private Integer httpStatus;
  private String description;
  private Long companyId;
  private Date createdAt;

  private ProductDTO productDTO;

}
