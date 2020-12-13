package io.inprice.common.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.inprice.common.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkSpec implements Serializable {

   private static final long serialVersionUID = -7641858030475659639L;

   private Long id;
   private Long linkId;
   private String key;
   private String value;
   private Long productId;

   @JsonIgnore
   private Long companyId;

   public LinkSpec(String key, String value) {
      this(null, key, value);
   }

   public LinkSpec(Long linkId, String key, String value) {
      this.linkId = linkId;
      this.key = StringUtils.fixQuotes(key.trim());
      this.value = StringUtils.fixQuotes(value.trim());
   }

}