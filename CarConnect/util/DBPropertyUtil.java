package com.java.CarConnect.util;

import java.util.ResourceBundle;

public class DBPropertyUtil {
	public static String getConnectionString(String propertyFile) {
		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		String connStr = rb.getString("url");
		return connStr;
	}

}
