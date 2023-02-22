package com.aristobit.ocrkata;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class OcrReaderTests {

	private OcrReader reader = null;
	
	@Test
	public void emptyReaderEmptyResults() {
		reader = OcrReader.empty();
		OcrResult result = reader.getResult();
		assertEquals("no account numbers expected", 0, result.getSize());
	}
	
	private void assertOcrCountFromFile(String filename, int expectedAccountCount) {
		OcrResult result = getOcrResultForFilename(filename);
		assertEquals("count of account numbers from ["+filename+"]", expectedAccountCount, result.getSize());
	}

	private OcrResult getOcrResultForFilename(String filename) {
		BufferedReader br = getBufferedFileReaderFrom(filename);
		reader = new OcrReader(br);
		OcrResult result = reader.getResult();
		return result;
	}
	
	private BufferedReader getBufferedFileReaderFrom(String filename) {
		InputStream ocrInputStream = this.getClass().getResourceAsStream(filename);
		return new BufferedReader(new InputStreamReader(ocrInputStream));
	}

	@Test
	public void testEmptyResultsFromFile() {
		assertOcrCountFromFile("/resources/empty.txt", 0);
	}

	@Test
	public void testFindAccount000000000() {
		OcrResult result = getOcrResultForFilename("/resources/000000000.txt");
		List<String> parsedAccounts = result.getParsedAccounts();
		assertEquals("first acct#", "000000000", parsedAccounts.get(0));
	}

	@Test
	public void testFindAccount123456789() {
		OcrResult result = getOcrResultForFilename("/resources/123456789.txt");
		List<String> parsedAccounts = result.getParsedAccounts();
		assertEquals("first acct#", "123456789", parsedAccounts.get(0));
	}

	// Pending numeric parsing of an input record
//	@Test
//	public void testUseCaseOneResultsFromFile() {
//		assertOcrResultsFromFile("/resources/use-case-1.txt", 11);
//	}
}
