package com.vinted.shipment.enums;

public enum PackageSize {
	S("S"), M("M"), L("L");

	private String value;

	PackageSize(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
