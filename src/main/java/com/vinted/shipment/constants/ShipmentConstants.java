package com.vinted.shipment.constants;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class ShipmentConstants {

	public static final String PACKAGE_SIZE_S = "S";

	public static final String PACKAGE_SIZE_M = "M";

	public static final String PACKAGE_SIZE_L = "L";

	public static final String PROVIDER_MR = "MR";

	public static final String PROVIDER_LP = "LP";

	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String EMPTY_SPACE = " ";
	
	public static final String SPACE_HIPEN = " -";
	
	public static final BigDecimal ZERO_PRICE = BigDecimal.ZERO.round(new MathContext(3, RoundingMode.DOWN));
	
	public static final BigDecimal PROVIDER_LP_SMALL_PACKAGE_DISCOUNT = new BigDecimal(0.50).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_LP_SMALL_PACKAGE_PRICE = new BigDecimal(1.50).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_LP_MEDIUM_PACKAGE_PRICE = new BigDecimal(4.90).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_LP_LARGE_PACKAGE_PRICE = new BigDecimal(6.90).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_MR_SMALL_PACKAGE_PRICE = new BigDecimal(2.00).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_MR_MEDIUM_PACKAGE_PRICE = new BigDecimal(3.00).round(new MathContext(3, RoundingMode.DOWN));

	public static final BigDecimal PROVIDER_MR_LARGE_PACKAGE_PRICE = new BigDecimal(4.00).round(new MathContext(3, RoundingMode.DOWN));

}
