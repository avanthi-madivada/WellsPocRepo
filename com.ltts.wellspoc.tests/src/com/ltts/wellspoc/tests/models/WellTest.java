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
    private Well setwellInstance;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wellInstance = new Well("Well 1", 420107.6, 7244305.1, 240.0, "Ghawar", "Not Fm. 2 HD Top", "Horizontal",
				false);
		setwellInstance=new Well();
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
		assertEquals("Test for easting attribute",wellInstance.getEasting(),420107.6 );
	}
	
	@Test
	public void test_getWellNorthing() {	
		assertNotNull(wellInstance.getNorthing());
		assertEquals("Test for northing attribute",wellInstance.getNorthing(),7244305.1);	
	}
	
	@Test
	public void test_getWellAzimuth() {		
		assertNotNull(wellInstance.getAzimuth());
		//assertTrue(checkForAzimuthRange(wellInstance.getAzimuth(), minValue, maxValue));
		assertEquals("Test for azimuth attribute",240.0,wellInstance.getAzimuth());
	}
	
	@Test
	public void test_getWellFieldName() {
		assertNotNull(wellInstance.getField());
		assertEquals("Test for well field attribute", "Ghawar", wellInstance.getField());
	}
	@Test
	public void test_getWellReservoir() 
	{
		assertNotNull(wellInstance.getReservoir());
		assertEquals("Test for well field attribute", "Not Fm. 2 HD Top", wellInstance.getReservoir());
	}
	@Test
	public void test_getWellType() {
		assertNotNull(wellInstance.getType());
		assertEquals("Test for well Type attribute", "Horizontal", wellInstance.getType());
	}
	
	// test cases for setters	
	@Test
	public void test_setWellPlanName() {
		String wellplanname="random";
		setwellInstance.setWellPlanName(wellplanname);
		assertEquals("Test for well name attribute", wellplanname, setwellInstance.getWellPlanName());
	}
	
	@Test
	public void test_setWellEasting() 
	{
		Double eastingValue=23400.67;
		setwellInstance.setEasting(eastingValue);
		assertEquals("Test for well easting attribute", eastingValue, setwellInstance.getEasting());
	}
	@Test
	public void test_setWellNorthing() 
	{
		Double northingValue=56000.67;
		setwellInstance.setNorthing(northingValue);
		assertEquals("Test for well northing attribute", northingValue, setwellInstance.getNorthing());
	}
	
	@Test
	public void test_setWellAzimuth() 
	{
		Double azimuthValue=130.0;
		setwellInstance.setAzimuth(azimuthValue);
		assertEquals("Test for well azimuth attribute", azimuthValue, setwellInstance.getAzimuth());
	}
	
	@Test
	public void test_setWellField()
	{
		String fieldValue="Kochi";
		setwellInstance.setField(fieldValue);
		assertEquals("Test for well azimuth attribute", fieldValue, setwellInstance.getField());
	}
	
	@Test
	public void test_setWellReservoir()
	{
		String reservoirValue="Not Fm. 2 HD Top";
		setwellInstance.setReservoir(reservoirValue);
		assertEquals("Test for well reservoir attribute", reservoirValue, setwellInstance.getReservoir());
	}
	
	@Test
	public void test_setWellType()
	{
		String typeValue="Deviated";
		setwellInstance.setType(typeValue);
		assertEquals("Test for well Type attribute", typeValue, setwellInstance.getType());
	}

}
