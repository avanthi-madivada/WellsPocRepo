package com.ltts.wellspoc.tests.ui.util;

import org.junit.Test;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import junit.framework.TestCase;

/**
 * Class with tests for methods of MessagesUtil.java.
 * 
 * @author padmaja
 *
 */
public class MessagesUtilTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_checkIfNumberValid() {
		boolean isValid = true;
		boolean actualValue;
		String text = "4000";
		assertNotNull(text);
		actualValue = MessagesUtil.checkIfNumber(text);

		assertEquals("Testcase for checkIfNumber method", isValid, actualValue);

	}

	@Test
	public void test_checkInLimit() {
		boolean isValid = true;
		boolean actualValue;
		String text = "4000";

		Double minValue = 24.00;
		Double maxValue = 250.00;

		assertNotNull(text);
		actualValue = MessagesUtil.checkInLimit(text, minValue, maxValue);
		assertEquals("Testcase for checkInLimit method", isValid, actualValue);

	}

	@Test
	public void test_restrictEnteredChars() {
		boolean isValid = true;
		boolean actualValue;
		String text = "4000";
		Double minValue = 12.00;
		Double maxValue = 350.00;

		assertNotNull(text);
		actualValue = MessagesUtil.restrictEnteredChars(text, minValue, maxValue);
		assertEquals("Testcase  for restrictEnteredChars method ", isValid, actualValue);

	}

}
