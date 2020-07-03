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
	
	@Test
	public void test_getWellEasting() {
		
		assertNotNull(wellInstance.getEasting());
		//assertTrue(checkForPositiveNumber(wellInstance.getEasting()));
		assertEquals("Test for easting attribute",checkForPositiveNumber(wellInstance.getEasting()),420107.6 );
	}
	
	//checking for positive number
	public Double checkForPositiveNumber(Double easting) {
		if(easting>0 && easting %1 !=0)
			{
			return easting;
			}
		else 
			return 0.0;
		}
	
	@Test
	public void test_getWellNorthing() {
		
		assertNotNull(wellInstance.getNorthing());
		//assertTrue(checkForPositiveNumber(wellInstance.getEasting()));
		assertEquals("Test for northing attribute",checkForPositiveNumber(wellInstance.getNorthing()),7244305.1);
	}
	
	@Test
	public void test_getWellAzimuth() {
		
		assertNotNull(wellInstance.getAzimuth());
		assertTrue(checkForAzimuthRange(wellInstance.getAzimuth()));
		assertEquals("Test for azimuth attribute",240.0,wellInstance.getAzimuth());
	}

	//check if the angle is between 0 and 360
	private boolean checkForAzimuthRange(Double angle) {
		if(angle>0&&angle<360)
			{
			return true;
			}
		else {
			return false;
		}
	}
	
	@Test
	public void test_getWellFieldName() {
		assertNotNull(wellInstance.getField());
		assertEquals("Test for well field attribute", "Ghawar", wellInstance.getField());
	}
	
	@Test
	public void test_getWellType() {
		assertNotNull(wellInstance.getType());
		assertEquals("Test for well Type attribute", "Horizontal", wellInstance.getType());
	}

}
