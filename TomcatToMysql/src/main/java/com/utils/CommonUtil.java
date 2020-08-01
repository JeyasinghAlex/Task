package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {
	
	public static Properties loadProperty() {
        Properties properties = new Properties();
        try (InputStream input = CommonUtil.class.getClassLoader().getResourceAsStream("StudentConfig.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
