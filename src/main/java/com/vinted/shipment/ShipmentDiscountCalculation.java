package com.vinted.shipment;

import java.util.List;

import com.vinted.shipment.helper.DiscountCalculationHelper;
import com.vinted.shipment.helper.FileReaderHelper;

public class ShipmentDiscountCalculation {
	public static void main(String[] args) {
		// Read the input data from 'input.txt' file
		String fileName = "input.txt";
		FileReaderHelper fileReader = new FileReaderHelper();
		List<String> transactinList = fileReader.readFileAsList(fileName);
		DiscountCalculationHelper calculationHelper = new DiscountCalculationHelper();
		transactinList.forEach(transaction -> {
			// validate the shipment discount for each transaction with given rules
			calculationHelper.validateTranstionByRules(transaction);
		});
	}
}
