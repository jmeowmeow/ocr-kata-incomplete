package com.aristobit.ocrkata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OcrReaderTests {

	private OcrReader reader = null;
	
	@Test
	public void EmptyReaderEmptyResults() {
		reader = OcrReader.empty();
		OcrResult result = reader.getResult();
		assertEquals("no account numbers expected", 0, result.getSize());
	}
}
