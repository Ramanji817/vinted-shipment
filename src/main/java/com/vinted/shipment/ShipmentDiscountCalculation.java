package com.vinted.shipment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.vinted.shipment.constants.ShipmentConstants;
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
		transactinList.forEach(transaction -> {
			//validate the transaction with given rules
			validateTranstionByRules(transaction);

		});
	}

	private static void validateTranstionByRules(String transaction) {
		String[] transactionDetails = transaction.split("\\s+");
		if (!validateShipmentType(transactionDetails)) {
			transaction = transaction + " Ignored";
		}
		transaction = appendShipmentDiscount(transaction);		
		System.out.println(transaction);
	}

	private static String appendShipmentDiscount(String transaction) {
		String[] transactionDetails = transaction.split("\\s+");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(transactionDetails[0], formatter);

		if (month != localDate.getMonthValue()) {
			month = localDate.getMonthValue();
			discount = 0.0;
			lpCount = 0;
		}
		switch (transactionDetails[1]) {
		case ShipmentConstants.PACKAGE_SIZE_S:
			if (discount <= 10 && ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
				if (discount > 9.50) {
					double value = 10.00 - discount;
					double value2 = 2.00 - Double.valueOf(String.format("%.2f", value));
					transaction = transaction + " " + String.format("%.2f", value2) + " "
							+ String.format("%.2f", value);
					discount = discount + value;
				} else {
					transaction = transaction + " 1.50 0.50";
					discount = discount + 0.50;
				}

			} else if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
				transaction = transaction + " 2.00 -";
			}

			if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
				transaction = transaction + " 1.50 -";
			}
			break;
		case ShipmentConstants.PACKAGE_SIZE_M:
			if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
				transaction = transaction + " 3.00 -";
			}
			if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
				transaction = transaction + " 4.90 -";
			}
			break;
		case ShipmentConstants.PACKAGE_SIZE_L:
			if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
				transaction = transaction + " 4.00 -";
			}

			if (lpCount == 3 && ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
				transaction = transaction + " 0.00 6.90";
				discount = discount + 6.90;
				lpCount++;
			} else if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
				lpCount++;
				transaction = transaction + " 6.90 -";
			}
			break;
		}
		return transaction;
	}
	
	private static boolean validateShipmentType(String[] transactionDetails) {
		switch (transactionDetails[1]) {
		case ShipmentConstants.PACKAGE_SIZE_S:
			return true;
		case ShipmentConstants.PACKAGE_SIZE_M:
			return true;
		case ShipmentConstants.PACKAGE_SIZE_L:
			return true;
		default:
			return false;
		}
	}

}
