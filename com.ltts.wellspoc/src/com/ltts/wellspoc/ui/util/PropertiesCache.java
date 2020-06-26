package com.ltts.wellspoc.ui.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {

	private final Properties configProp = new Properties();
	
	/*Private constructor to restrict new instances */
	PropertiesCache() 
	{
	
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("resource.properties");
		try {
			configProp.load(in);
			} 
		catch (IOException e)
		{
        e.printStackTrace();
    }
	}

	/*Bill pugh solution for singleton pattern using static inner class
	 * which will be initialized when instance is required 
	 * and other static members are still accessible
	 */
	private static class LazyHolder
	{
    private static final PropertiesCache INSTANCE = new PropertiesCache();
	}

	/*This method returns an instance of singleton class*/
	
	public static PropertiesCache getInstance()
	{
    return LazyHolder.INSTANCE;
	}

	/*This method is used to access value of individual property
	 */  

	public String getProperty(String key){
    return configProp.getProperty(key);
	}

	/*This method is used to access all property names
	 */ 
	
	public Set<String> getAllPropertyNames(){
    return configProp.stringPropertyNames();
	}

	/*This method checks whether a property contains 
	 * a value for particular key
	 */
	
	public boolean containsKey(String key){
    return configProp.containsKey(key);
	}
}
