package com.homeaway.test.config;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
	private final static String fileName = "/config.properties";
	private static Properties p;
	
	public static Properties getConfigProperties(){
		
		try {
			InputStream is = TestConfig.class.getResourceAsStream(fileName);
			
			if (null == is){
				throw new RuntimeException("Unable to find configuration file.");
			}
			
			p = new Properties();
			p.load(is);
			return p;
		} catch (Exception e){
			throw new RuntimeException("Unable to read configuration file.");
		}
	}
}