package io.inprice.common.info;

import java.util.Map;

import io.inprice.common.meta.EmailTemplate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailData {

	private EmailTemplate template;
	private String from;
	private String to;
	private String subject;
	private Map<String, Object> data;
	
}
