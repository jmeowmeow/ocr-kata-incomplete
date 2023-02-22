package com.aristobit.ocrkata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
			accountNumber = parseOcrRecord(accountRecord);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return accountNumber;
	}

	// 0123 ran of time typing
    private static final String[] ocrDigits = new String[] {
     " _ | ||_|",
     "     |  |",
     " _  _||_ ",
     " _  _| _|"

    };
    		
	private String parseOcrRecord(String[] accountRecord) {
		// step through the top three lines three spaces at a time
		// to create a digit, compare the digit to ocrDigits
		// and concatenate the digits to form an account number
		StringBuilder buf = new StringBuilder();
		int column = 0;
		while (column < 27) {
			StringBuilder pattern = new StringBuilder();
			pattern = pattern.append(accountRecord[0].substring(column, column+2));
			pattern = pattern.append(accountRecord[1].substring(column, column+2));
			pattern = pattern.append(accountRecord[2].substring(column, column+2));
			// ignore 4th line presumed blank
			
			// should use a Map key but ran out of time
			if (ocrDigits[0].equals(pattern)) {
				buf.append("0");
			} else if (ocrDigits[1].equals(pattern)) {
				buf.append("1");
			} else if (ocrDigits[2].equals(pattern)) {
				buf.append("2");
			} else if (ocrDigits[3].equals(pattern)) {
				buf.append("3");
			}
			column += 3;
		}
		return buf.toString();
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
