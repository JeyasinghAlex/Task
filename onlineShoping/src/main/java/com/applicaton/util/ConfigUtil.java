package com.applicaton.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	public static Properties loadProperty() {
		Properties properties = new Properties();
		try {
			InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("DbConfig.properties");
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}
}
