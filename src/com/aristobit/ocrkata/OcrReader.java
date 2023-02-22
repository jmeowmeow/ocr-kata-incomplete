package com.aristobit.ocrkata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Takes a BufferedReader and reads line by line,
 * interpreting the OCR line characters
 * into a sequence of account numbers.
 */
public class OcrReader {

	private BufferedReader ocrInputReader;
	private OcrResult ocrResult;

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
		// stand-in for input file processing
		try {
			while (ocrInputReader.readLine() != null) { }
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ocrResult = new OcrResult();
	}

}
