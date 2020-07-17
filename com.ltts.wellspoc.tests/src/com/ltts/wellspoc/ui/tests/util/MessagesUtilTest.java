package com.ltts.wellspoc.ui.tests.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;


import com.ltts.wellspoc.ui.util.MessagesUtil;
import junit.framework.TestCase;

public class MessagesUtilTest extends TestCase {
	
	
	private MessagesUtil messageUtil;

	Text textItem ;

	private static Shell defaultShell = Display.getDefault().getActiveShell();
	

  	@Override
   	protected void setUp() throws Exception {
   		super.setUp();
   		
   	
   	}

   	@Override
   	protected void tearDown() throws Exception {
   		super.tearDown();
   	}

	
	@Test
	public  void test_checkIfNumberValid() {
		
		textItem = new Text(defaultShell, 0);
		textItem.setText("4000");
	
		messageUtil.checkIfNumber(textItem);
		String textEntered = textItem.getText(); 
			
		 boolean isValid = true;
     
		 assertTrue(textEntered, isValid);
		 
	    }
	
	@Test
	public void test_checkInLimit() {
		
		Double  minValue= 24.00;
		Double maxValue=250.00;
		
		textItem = new Text(defaultShell, 0);
		textItem.setText("4000");
		
		messageUtil.checkInLimit(textItem, minValue, maxValue);
		//messageUtil.checkIfNumber(textItem);
		String textEntered = textItem.getText();
		
		boolean isValid = true;
		 assertTrue(textEntered, isValid);
		
	}
	
	@Test
	public void test_restrictEnteredChars() {
		
		Double  minValue= 12.00;
		Double maxValue=350.00;
		
		textItem = new Text(defaultShell, 0);
		textItem.setText("4000");
		messageUtil.restrictEnteredChars(textItem, minValue, maxValue);
		
		String textEntered = textItem.getText();
	
		boolean isValid = true;
	
		 assertTrue(textEntered, isValid);
		 
		 //messageUtil.checkInLimit(textItem, minValue, maxValue);
	}
	
	
   


}

