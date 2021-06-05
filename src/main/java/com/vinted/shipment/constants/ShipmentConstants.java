package com.vinted.shipment.constants;

import java.math.BigDecimal;

public final class ShipmentConstants {

	public static final String PACKAGE_SIZE_S = "S";

	public static final String PACKAGE_SIZE_M = "M";

	public static final String PACKAGE_SIZE_L = "L";

	public static final String PROVIDER_MR = "MR";

	public static final String PROVIDER_LP = "LP";

	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

	public static final String EMPTY_SPACE = " ";

	public static final String SPACE_HIPEN = " -";

	public static final BigDecimal ZERO_PRICE = BigDecimal.ZERO;
	
	public static final BigDecimal MAX_DISCOUNT_VALUE = new BigDecimal(10.00);
	
	public static final BigDecimal SMALL_DISCOUNT_LIMIT = new BigDecimal(9.50);

	public static final BigDecimal PROVIDER_LP_SMALL_PACKAGE_DISCOUNT = new BigDecimal(0.50);

	public static final BigDecimal PROVIDER_LP_SMALL_PACKAGE_PRICE = new BigDecimal(1.50);

	public static final BigDecimal PROVIDER_LP_MEDIUM_PACKAGE_PRICE = new BigDecimal(4.90);

	public static final BigDecimal PROVIDER_LP_LARGE_PACKAGE_PRICE = new BigDecimal(6.90);

	public static final BigDecimal PROVIDER_MR_SMALL_PACKAGE_PRICE = new BigDecimal(2.00);

	public static final BigDecimal PROVIDER_MR_MEDIUM_PACKAGE_PRICE = new BigDecimal(3.00);

	public static final BigDecimal PROVIDER_MR_LARGE_PACKAGE_PRICE = new BigDecimal(4.00);

}
