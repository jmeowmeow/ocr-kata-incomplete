package com.aristobit.ocrkata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Takes a BufferedReader and reads line by line,
 * interpreting the OCR line characters
 * into a sequence of account numbers.
 */
public class OcrReader {

	private static final int OCR_RECORD_LENGTH = 4;
	private BufferedReader ocrInputReader; // consumed once
	private OcrResult ocrResult; // produced once

	public static void main(String[] args) throws Exception {
		OcrMain.main(args);
	}

	public OcrReader(BufferedReader inputReader) {
		this.ocrInputReader = inputReader;
	}

	public static OcrReader empty() {
		BufferedReader emptyReader = new BufferedReader(new StringReader(""));
		return new OcrReader(emptyReader);
	}

	public OcrResult getResult() {
		if (ocrResult == null) {
			createOcrResult();
		}
		return ocrResult;
	}

	private void createOcrResult() {
		List<String> parsedAccounts = new ArrayList<String>();
		String accountNumber;
		while ((accountNumber = parseNextAccount()) != null) {
			parsedAccounts.add(accountNumber);
		}
		ocrResult = new OcrResult(parsedAccounts);
	}

	private String parseNextAccount() {
		String[] accountRecord = new String[OCR_RECORD_LENGTH];
		String accountNumber = "000000000";
		try {
			accountRecord[0] = ocrInputReader.readLine();
			accountRecord[1] = ocrInputReader.readLine();
			accountRecord[2] = ocrInputReader.readLine();
			accountRecord[3] = ocrInputReader.readLine();
			if (anyNull(accountRecord)) {
				return null;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return accountNumber;
	}

	private boolean anyNull(String[] accountRecord) {
		for (String line : accountRecord) {
			if (line == null) {
				return true;
			}
		}
		return false;
	}

}
