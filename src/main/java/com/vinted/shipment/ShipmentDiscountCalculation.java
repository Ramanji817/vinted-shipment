package com.vinted.shipment;

import java.util.List;

import com.vinted.shipment.helper.DiscountCalculationHelper;
import com.vinted.shipment.helper.FileReaderHelper;

public class ShipmentDiscountCalculation {

	static int month = 0;
	static double discount = 0.0;
	static int lpCount = 0;

	public static void main(String[] args) {
		// Read the input data from 'input.txt' file
		String fileName = "input.txt";
		FileReaderHelper fileReader = new FileReaderHelper();
		List<String> transactinList = fileReader.readFileAsList(fileName);		
		DiscountCalculationHelper calculationHelper = new DiscountCalculationHelper();
		transactinList.forEach(transaction -> {
			// validate the transaction with given rules
			calculationHelper.validateTranstionByRules(transaction, month, discount, lpCount);
		});
	}
}
