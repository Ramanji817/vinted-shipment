package com.vinted.shipment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.vinted.shipment.helper.FileReaderHelper;

public class ShipmentDiscountCalculation {

	static int month = 0;
	static double discount = 0.0;
	static int lpCount = 0;

	public static void main(String[] args) {

		// Read the input data from 'input.txt' file.
		String fileName = "input.txt";

		FileReaderHelper fileReader = new FileReaderHelper();
		List<String> transactinList = fileReader.readFileAsList(fileName);
		transactinList.forEach(transaction -> {
			// rule1

			validateRule1(transaction);

		});

		// Follow below rules while implementing ShipmentDiscount.
		// Rule-1 : All S shipments should always match the lowest S package price among
		// the providers.

		// Rule-2 : Third L shipment via LP should be free, but only once a calendar
		// month.

		// Rule-3 : Accumulated discounts cannot exceed 10 € in a calendar month. If
		// there are not enough funds to fully cover a discount this calendar month, it
		// should be covered partially.

		// Output display

	}

	private static void validateRule1(String transaction) {
		String[] transactionDetails = transaction.split("\\s+");
		if (!validateShipmentType(transactionDetails)) {
			transaction = transaction + " Ignored";
		}
		transaction = appendShipmentDiscount(transaction);

		if (validateRule2(transactionDetails)) {

		}

		System.out.println(transaction);

	}

	private static String appendShipmentDiscount(String transaction) {
		// DecimalFormat df = new DecimalFormat("#.##");
		String[] transactionDetails = transaction.split("\\s+");
		Date date = formatdate(transactionDetails[0]);
		month = date.getMonth();
		if (month != date.getMonth()) {
			discount = 0.0;
			lpCount = 0;
		}
		switch (transactionDetails[1]) {
		case "S":
			if (discount <= 10 && "MR".contains(transactionDetails[2])) {
				if (discount > 9.50) {
					double value = 10.00 - discount;
					// double value2 = 2.00 - Double.valueOf(df.format(value));
					// transaction = transaction + " " + df.format(value2) + " " + df.format(value);
					double value2 = 2.00 - Double.valueOf(String.format("%.2f", value));
					transaction = transaction + " " + String.format("%.2f", value2) + " "
							+ String.format("%.2f", value);
				} else {
					transaction = transaction + " 1.50 0.50";
					discount = discount + 0.50;
				}

			} else if ("MR".contains(transactionDetails[2])) {
				transaction = transaction + " 2.00 -";
			}

			if ("LP".contains(transactionDetails[2])) {
				transaction = transaction + " 1.50 -";
			}
			break;
		case "M":
			if ("MR".contains(transactionDetails[2])) {
				transaction = transaction + " 3.00 -";
			}
			if ("LP".contains(transactionDetails[2])) {
				transaction = transaction + " 4.90 -";
			}
			break;
		case "L":
			if ("MR".contains(transactionDetails[2])) {
				transaction = transaction + " 4.00 -";
			}

			if (lpCount == 3 && "LP".contains(transactionDetails[2])) {
				transaction = transaction + " 0.00 6.90";
				discount = discount + 6.90;
				lpCount++;
			} else if ("LP".contains(transactionDetails[2])) {
				lpCount++;
				transaction = transaction + " 6.90 -";
			}
			break;
		}

		return transaction;
	}

	private static Date formatdate(String string) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	private static boolean validateRule2(String[] transactionDetails) {

		return false;
	}

	private static boolean validateShipmentType(String[] transactionDetails) {
		switch (transactionDetails[1]) {
		case "S":
			return true;
		case "M":
			return true;
		case "L":
			return true;
		default:
			return false;
		}
	}

}
