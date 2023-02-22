package com.aristobit.ocrkata;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Get the lights on with JUnit 4
 */
public class HelloWorldTests {

	@Test
	public void lightsAreOn() {
		assertTrue("Hello world should contain \"world\"", "Hello world".contains("world"));
	}

	@Test
	public void seeItFailThenFix() {
		assertFalse("Hello world should not contain \"weird\"", "Hello world".contains("weird"));		
	}
}
