package com.aristobit.ocrkata;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class OcrReaderTests {

	private OcrReader reader = null;
	
	@Test
	public void emptyReaderEmptyResults() {
		reader = OcrReader.empty();
		OcrResult result = reader.getResult();
		assertEquals("no account numbers expected", 0, result.getSize());
	}
	
	private void assertOcrResultsFromFile(String filename, int expectedAccountCount) {
		BufferedReader br = getBufferedFileReaderFrom(filename);
		reader = new OcrReader(br);
		OcrResult result = reader.getResult();
		assertEquals("count of account numbers from ["+filename+"]", expectedAccountCount, result.getSize());
	}
	
	private BufferedReader getBufferedFileReaderFrom(String filename) {
		InputStream ocrInputStream = this.getClass().getResourceAsStream(filename);
		return new BufferedReader(new InputStreamReader(ocrInputStream));
	}

	@Test
	public void testEmptyResultsFromFile() {
		assertOcrResultsFromFile("/resources/empty.txt", 0);
	}

	// Pending numeric parsing of an input record
//	@Test
//	public void testUseCaseOneResultsFromFile() {
//		assertOcrResultsFromFile("/resources/use-case-1.txt", 11);
//	}
}
