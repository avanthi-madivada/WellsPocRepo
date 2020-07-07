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
	public static boolean isValid = true;
	// Min and Max value for azimuth
	Double minValue = 1.0;
	Double maxValue = 360.0;
	
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
		assertTrue(checkIfNumber(wellInstance.getEasting()));
		assertEquals("Test for easting attribute",wellInstance.getEasting(),420107.6 );
	}
	
	//checking if entered  number is positive
	public boolean checkIfNumber(Double value) {
		try {
			if(value>0&& value%1 !=0)
			{
				isValid=true;
				return isValid;		
			}		 
		} catch (NumberFormatException exception) {
			isValid = false;		
			return isValid;
		}
		return isValid;
	}
	
	//northing field
	@Test
	public void test_getWellNorthing() {
		
		assertNotNull(wellInstance.getNorthing());
		assertTrue(checkIfNumber(wellInstance.getNorthing()));
		assertEquals("Test for northing attribute",wellInstance.getNorthing(),7244305.1);
	}
	
	//azimuth field
	@Test
	public void test_getWellAzimuth() {
		
		assertNotNull(wellInstance.getAzimuth());
		assertTrue(checkForAzimuthRange(wellInstance.getAzimuth(), minValue, maxValue));
		assertEquals("Test for azimuth attribute",240.0,wellInstance.getAzimuth());
	}
	
	
	@Test
	public void test_getWellFieldName() {
		assertNotNull(wellInstance.getField());
		assertEquals("Test for well field attribute", "Ghawar", wellInstance.getField());
	}
	
	//type field
	@Test
	public void test_getWellType() {
		assertNotNull(wellInstance.getType());
		assertEquals("Test for well Type attribute", "Horizontal", wellInstance.getType());
	}

	
	//check if the angle is between 0 and 360
		private boolean checkForAzimuthRange(Double value,Double minValue, Double maxValue) {
			checkIfNumber(value);
			try {
				Double enteredValue = Double.valueOf(value);
				if (enteredValue < minValue || enteredValue > maxValue) {		
					isValid= false;
					return isValid;
				} 
				else {
					isValid= true;
					return isValid;
				}
			} catch (NumberFormatException exception) {
				return false;
			}
		}
	
	
}
