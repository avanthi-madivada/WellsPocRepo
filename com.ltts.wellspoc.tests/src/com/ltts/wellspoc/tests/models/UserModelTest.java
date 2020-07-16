package com.ltts.wellspoc.tests.models;

import org.junit.Test;

import com.ltts.wellspoc.models.UserModel;

import junit.framework.TestCase;


/**
 * Class with tests for all the attributes of UserModel.java.
 * 
 * @author Padmaja
 *
 */

public class UserModelTest  extends TestCase{
	
	private UserModel userInstance;
    private UserModel setUserInstance;
  
	
   	@Override
   	protected void setUp() throws Exception {
   		super.setUp();
   		userInstance = new UserModel("admin","admin");
   				
   		setUserInstance=new UserModel();
   	}

   	@Override
   	protected void tearDown() throws Exception {
   		super.tearDown();
   	}
    

	@Test
	public void test_getUserName() {
		assertNotNull(userInstance.getUserName());
		assertEquals("Here is the test for UserName", "admin", userInstance.getUserName());
	}
	@Test
	public void test_getPassword() {
		
		assertNotNull(userInstance.getPassword());
		assertEquals("Here is the test for Password","admin",userInstance.getPassword());
	}
	
	
	// setter test cases

	@Test
	public void test_setUserName() {
		String userName="admin";
		setUserInstance.setUserName(userName);
		assertEquals("Test for UserName attribute", userName, setUserInstance.getUserName());
	}
	
	@Test
	public void test_setPassword() 
	{
		String password="admin";
		
		setUserInstance.setPassword(password);
		assertEquals("Test for PassWord attribute", password, setUserInstance.getPassword());
	}
}
