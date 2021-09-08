package io.inprice.common.info;

import java.io.Serializable;
import java.util.Map;

import io.inprice.common.meta.EmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailData implements Serializable {

	private static final long serialVersionUID = -3982565659337086190L;

	private EmailTemplate template;
	private String from;
	private String to;
	private String subject;
	private Map<String, Object> data;
	
}
