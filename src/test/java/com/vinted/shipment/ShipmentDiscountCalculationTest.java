package com.vinted.shipment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShipmentDiscountCalculationTest {

	private ShipmentDiscountCalculation shipmentDiscountCalculation = new ShipmentDiscountCalculation();

	@Test
	void testClassName() {
		assertEquals("com.vinted.shipment.ShipmentDiscountCalculation",
				shipmentDiscountCalculation.getClass().getName());
	}

	@Test
	void testInputFileNamae() {
		assertEquals("input.txt", ShipmentDiscountCalculation.fileName);
	}

}
