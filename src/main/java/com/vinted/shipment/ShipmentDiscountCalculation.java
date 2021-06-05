package com.vinted.shipment;

import java.util.List;

import com.vinted.shipment.helper.DiscountCalculationHelper;
import com.vinted.shipment.helper.FileReaderHelper;

public class ShipmentDiscountCalculation {
	
	public final static String fileName = "input.txt";
	public static void main(String[] args) {
		// Read the input data from 'input.txt' file		
		FileReaderHelper fileReader = new FileReaderHelper();
		List<String> transactinList = fileReader.readFileAsList(fileName);
		DiscountCalculationHelper calculationHelper = new DiscountCalculationHelper();
		transactinList.forEach(transaction -> {
			// validate the shipment discount for each transaction with given rules
			transaction = calculationHelper.validateTranstionByRules(transaction);
			System.out.println(transaction);
		});
	}
}
