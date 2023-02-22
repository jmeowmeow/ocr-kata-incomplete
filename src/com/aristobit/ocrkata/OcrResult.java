package com.aristobit.ocrkata;

import java.util.List;

public class OcrResult {

	private List<String> parsedAccounts = List.of("000000000");

	public OcrResult(List<String> parsedAccounts) {
		this.parsedAccounts = parsedAccounts;
	}

	public int getSize() {
		return parsedAccounts.size();
	}

	public List<String> getParsedAccounts() {
		return parsedAccounts;
	}

}
