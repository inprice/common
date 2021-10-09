package io.inprice.common.info;

import java.math.BigDecimal;

import io.inprice.common.meta.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRefreshResult {

	private BigDecimal minPrice = BigDecimal.ZERO;
	private BigDecimal avgPrice = BigDecimal.ZERO;
	private BigDecimal maxPrice = BigDecimal.ZERO;
	private Position position = Position.UNKNOWN;
	private Long alarmId;

	private Integer actives = 0;
	private Integer waitings = 0;
	private Integer tryings = 0;
	private Integer problems = 0;

}
