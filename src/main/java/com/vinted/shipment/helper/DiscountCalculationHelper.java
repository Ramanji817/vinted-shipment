package com.vinted.shipment.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vinted.shipment.constants.ShipmentConstants;

public class DiscountCalculationHelper {

	private int month = 0;
	private BigDecimal discount = BigDecimal.ZERO;
	private int lpCount = 0;

	public void validateTranstionByRules(String transaction) {
		String[] transactionDetails = transaction.split("\\s+");
		if (!validateShipmentType(transactionDetails)) {
			transaction = transaction + " Ignored";
		}
		transaction = appendShipmentDiscount(transaction);
		System.out.println(transaction);
	}

	private String appendShipmentDiscount(String transaction) {
		String[] transactionDetails = transaction.split("\\s+");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ShipmentConstants.ISO_DATE_FORMAT);
		LocalDate localDate = LocalDate.parse(transactionDetails[0], formatter);

		if (month != localDate.getMonthValue()) {
			month = localDate.getMonthValue();
			discount = BigDecimal.ZERO;
			lpCount = 0;
		}
		switch (transactionDetails[1]) {
		case ShipmentConstants.PACKAGE_SIZE_S:
			transaction = mapSmallPackage(transaction, transactionDetails);
			break;
		case ShipmentConstants.PACKAGE_SIZE_M:
			transaction = mapMediumPackage(transaction, transactionDetails);
			break;
		case ShipmentConstants.PACKAGE_SIZE_L:
			transaction = mapLargePacage(transaction, transactionDetails);
			break;
		}
		return transaction;
	}

	private String mapLargePacage(String transaction, String[] transactionDetails) {
		if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_MR_LARGE_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
		}

		if (lpCount == 3 && ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE + formatValue(ShipmentConstants.ZERO_PRICE)
					+ ShipmentConstants.EMPTY_SPACE + formatValue(ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE);
			discount = discount.add(formatValue(ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE));
			lpCount++;
		} else if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_LP_LARGE_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
			lpCount++;
		}
		return transaction;
	}

	private String mapMediumPackage(String transaction, String[] transactionDetails) {
		if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_MR_MEDIUM_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
		}
		if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_LP_MEDIUM_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
		}
		return transaction;
	}

	private String mapSmallPackage(String transaction, String[] transactionDetails) {
		if (discount.doubleValue() <= ShipmentConstants.MAX_DISCOUNT_VALUE.doubleValue()
				&& ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			if (discount.doubleValue() > ShipmentConstants.SMALL_DISCOUNT_LIMIT.doubleValue()) {
				BigDecimal balanceDiscount = ShipmentConstants.MAX_DISCOUNT_VALUE.subtract(discount);
				BigDecimal smallPacagePrice = ShipmentConstants.PROVIDER_MR_SMALL_PACKAGE_PRICE
						.subtract(balanceDiscount);

				transaction = transaction + ShipmentConstants.EMPTY_SPACE + formatValue(smallPacagePrice)
						+ ShipmentConstants.EMPTY_SPACE + formatValue(balanceDiscount);
				discount = discount.add(balanceDiscount);
			} else {
				transaction = transaction + ShipmentConstants.EMPTY_SPACE
						+ formatValue(ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_PRICE) + ShipmentConstants.EMPTY_SPACE
						+ formatValue(ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_DISCOUNT);
				discount = discount.add(ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_DISCOUNT);
			}

		} else if (ShipmentConstants.PROVIDER_MR.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_MR_SMALL_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
		}

		if (ShipmentConstants.PROVIDER_LP.contains(transactionDetails[2])) {
			transaction = transaction + ShipmentConstants.EMPTY_SPACE
					+ formatValue(ShipmentConstants.PROVIDER_LP_SMALL_PACKAGE_PRICE) + ShipmentConstants.SPACE_HIPEN;
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

	private BigDecimal formatValue(BigDecimal value) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new BigDecimal(df.format(value));

	}

}
