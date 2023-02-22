package com.aristobit.ocrkata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A command-line runner for OcrReader.
 */
public class OcrMain {

	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 1) {
			System.out.println("OcrReader needs at least one input file name to process.");
			return;
		} else {
			for (String filename : args) {
				processOneFilename(filename);
			}
		}
		
	}

	private static void processOneFilename(String filename) throws FileNotFoundException {
		File file = new File(filename);
		System.out.println("Input OCR file: ["+ file.getPath() + "]");
		if (!file.exists()) {
			System.out.println("Can't find file.");
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		OcrReader ocrReader = new OcrReader(br);
		OcrResult result = ocrReader.getResult();
		System.out.println("The OcrReader found "+result.getSize()+" account numbers.");
	}

}
