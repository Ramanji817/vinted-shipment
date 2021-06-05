package com.vinted.shipment.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileReaderHelperTest {

	private FileReaderHelper fileReaderHelper;

	@BeforeEach
	void setUp() throws Exception {
		fileReaderHelper = new FileReaderHelper();
	}

	@Test
	void testClassNmae() {
		assertEquals("com.vinted.shipment.helper.FileReaderHelper", fileReaderHelper.getClass().getName());
	}

	@Test
	void testReadFileAsList() {
		assertNotNull(fileReaderHelper.readFileAsList("input.txt"));
	}

}
