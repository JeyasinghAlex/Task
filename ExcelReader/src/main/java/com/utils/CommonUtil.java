package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.model.Extension;

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
