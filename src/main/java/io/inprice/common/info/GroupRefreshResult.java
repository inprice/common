package io.inprice.common.info;

import java.math.BigDecimal;

import io.inprice.common.meta.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupRefreshResult {

	private BigDecimal minPrice = BigDecimal.ZERO;
	private BigDecimal avgPrice = BigDecimal.ZERO;
	private BigDecimal maxPrice = BigDecimal.ZERO;
	private BigDecimal total = BigDecimal.ZERO;
	private Level level = Level.NA;

}
