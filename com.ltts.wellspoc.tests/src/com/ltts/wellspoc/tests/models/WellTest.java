package com.ltts.wellspoc.tests.models;

import org.junit.Test;
import com.ltts.wellspoc.models.Well;
import junit.framework.TestCase;

/**
 * Class with tests for all the attributes of Well.java.
 * 
 * @author Avanthi
 *
 */
public class WellTest extends TestCase {

	private Well wellInstance;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wellInstance = new Well("Well 1", 420107.6, 7244305.1, 240.0, "Ghawar", "Not Fm. 2 HD Top", "Horizontal",
				false);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_getWellPlanName() {
		assertNotNull(wellInstance.getWellPlanName());
		assertEquals("Here is the test for well name attribute", "Well 1", wellInstance.getWellPlanName());
	}

}
