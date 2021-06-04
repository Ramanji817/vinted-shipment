package com.vinted.shipment.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vinted.shipment.constants.ShipmentConstants;

public class DiscountCalculationHelper {

	public void validateTranstionByRules(String transaction, int month, double discount, int lpCount) {
		String[] transactionDetails = transaction.split("\\s+");
		if (!validateShipmentType(transactionDetails)) {
			transaction = transaction + " Ignored";
		}
		transaction = appendShipmentDiscount(transaction, month, discount, lpCount);
		System.out.println(transaction);
	}

	private String appendShipmentDiscount(String transaction, int month, double discount, int lpCount) {
		String[] transactionDetails = transaction.split("\\s+");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ShipmentConstants.ISO_DATE_FORMAT);
		LocalDate localDate = LocalDate.parse(transactionDetails[0], formatter);

		if (month != localDate.getMonthValue()) {
			month = localDate.getMonthValue();
			discount = 0.0;
			lpCount = 0;
		}
		switch (transactionDetails[1]) {
		case ShipmentConstants.PACKAGE_SIZE_S:
			transaction = mapSmallPackage(transaction, discount, transactionDetails);
			break;
		case ShipmentConstants.PACKAGE_SIZE_M:
			transaction = mapMediumPackage(transaction, transactionDetails);
			break;
		case ShipmentConstants.PACKAGE_SIZE_L:
			transaction = mapLargePacage(transaction, discount, lpCount, transactionDetails);
			break;
		}
		return transaction;
	}

	private String mapLargePacage(String transaction, double discount, int lpCount, String[] transactionDetails) {
		if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_MR_LARGE_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}

		if (lpCount == 3 && ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE + ShipmentConstants.ZERO_PRICE
					+ ShipmentConstants.EMPTY_SPACE + ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE;
			discount = discount + ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE.doubleValue();
			lpCount++;
		} else if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			lpCount++;
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}
		return transaction;
	}

	private String mapMediumPackage(String transaction, String[] transactionDetails) {
		if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_MR_MEDIUM_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}
		if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_LP_MEDIUM_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}
		return transaction;
	}

	private String mapSmallPackage(String transaction, double discount, String[] transactionDetails) {
		if (discount <= 10 && ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			if (discount > 9.50) {
				double value = 10.00 - discount;
				double value2 = 2.00 - Double.valueOf(String.format("%.2f", value));
				transaction = transaction + " " + String.format("%.2f", value2) + " " + String.format("%.2f", value);
				discount = discount + value;
			} else {
				transaction = transaction + ShipmentConstants.EMPTY_SPACE
						+ ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_PRICE + ShipmentConstants.EMPTY_SPACE
						+ ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_DISCOUNT;
				discount = discount + ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_DISCOUNT.doubleValue();
			}

		} else if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_MR_SMALL_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}

		if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_PRICE + ShipmentConstants.SPACE_HIPEN;
		}
		return transaction;
	}

	private boolean validateShipmentType(String[] transactionDetails) {
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
