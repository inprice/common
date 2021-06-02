package io.inprice.common.models;

import io.inprice.common.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LinkSpec extends BaseModel {

   private static final long serialVersionUID = -7641858030475659639L;

   private Long linkId;
   private String key;
   private String value;
   private Long groupId;

   public LinkSpec(String key, String value) {
      this(null, key, value);
   }

   public LinkSpec(Long linkId, String key, String value) {
      this.linkId = linkId;
      this.key = StringUtils.fixQuotes(key.trim());
      this.value = StringUtils.fixQuotes(value.trim());
   }

}
