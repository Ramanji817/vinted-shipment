package com.vinted.shipment.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderHelper {

	public List<String> readFileAsList(String fileName) {

		List<String> transactionList = new ArrayList<>();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (sc.hasNextLine())
			transactionList.add(sc.nextLine());

		return transactionList;

	}

}
