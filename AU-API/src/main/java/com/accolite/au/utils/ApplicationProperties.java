package com.accolite.au.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;


public class ApplicationProperties {
    private static Properties properties;

    public static void setApplicationProperties() {
        // application.properties located at src/main/resource
        Resource resource = new ClassPathResource("/application.properties");
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getPropertyData(String propertyName) {
        setApplicationProperties();
        return properties.getProperty(propertyName);
    }
}