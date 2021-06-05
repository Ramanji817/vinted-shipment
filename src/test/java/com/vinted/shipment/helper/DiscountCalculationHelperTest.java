package com.vinted.shipment.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountCalculationHelperTest {

	private DiscountCalculationHelper discountCalculationHelper;

	@BeforeEach
	void setUp() throws Exception {
		discountCalculationHelper = new DiscountCalculationHelper();
	}

	@Test
	void testValidateClassName() {
		assertEquals("com.vinted.shipment.helper.DiscountCalculationHelper",
				discountCalculationHelper.getClass().getName());
	}

	@Test
	void testValidateTranstionByRules() {
		assertNotNull(discountCalculationHelper.validateTranstionByRules("2015-02-01 S MR"));
	}

	@Test
	void testValidateInvalidTransactionn() {
		assertEquals("2015-02-29 CUSPS Ignored",
				discountCalculationHelper.validateTranstionByRules("2015-02-29 CUSPS"));
	}

	@Test
	void testValidateSmallPackgeMRProvider() {
		
		assertEquals("2015-02-01 S MR 1.50 0.50",
				discountCalculationHelper.validateTranstionByRules("2015-02-01 S MR"));
	}
	
	@Test
	void testValidateSmallPackgeLPProvider() {
		assertEquals("2015-02-05 S LP 1.50 -",
				discountCalculationHelper.validateTranstionByRules("2015-02-05 S LP"));
	}
	
	@Test
	void testValidateMediumPackgeMRProvider() {
		assertEquals("2015-02-08 M MR 3.00 -",
				discountCalculationHelper.validateTranstionByRules("2015-02-08 M MR"));
	}
	
	@Test
	void testValidateMediumPackgeLPProvider() {
		assertEquals("2015-02-13 M LP 4.90 -",
				discountCalculationHelper.validateTranstionByRules("2015-02-13 M LP"));
	}
	
	@Test
	void testValidateLargePackgeMRProvider() {
		assertEquals("2015-02-07 L MR 4.00 -",
				discountCalculationHelper.validateTranstionByRules("2015-02-07 L MR"));
	}
	
	@Test
	void testValidateLargePackgeLPProvider() {
		assertEquals("2015-02-06 L LP 6.90 -",
				discountCalculationHelper.validateTranstionByRules("2015-02-06 L LP"));
	}

	

}
