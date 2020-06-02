package io.inprice.scrapper.common.models;

import java.io.Serializable;

import io.inprice.scrapper.common.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitorSpec implements Serializable {

   private static final long serialVersionUID = -7641858030475659639L;

   private Long id;
   private Long competitorId;
   private String key;
   private String value;
   private Long companyId;

   public CompetitorSpec(String key, String value) {
      this(null, key, value);
   }

   public CompetitorSpec(Long competitorId, String key, String value) {
      this.competitorId = competitorId;
      this.key = StringUtils.fixQuotes(key.trim());
      this.value = StringUtils.fixQuotes(value.trim());
   }

}
