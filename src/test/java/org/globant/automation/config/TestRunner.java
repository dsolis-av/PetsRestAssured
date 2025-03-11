package org.globant.automation.config;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRunner {
    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();
    private static String baseUrl;

    //Method to set up environment properties. To be executed before the suite starts
    @BeforeSuite
    public void setUpEnvironment() {
        loadProperties();
        baseUrl = getConfigVariable("url.base");
    }

    //Load properties from config files
    private void loadProperties(){
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Error loading properties file");
        }
    }

    private String getConfigVariable(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

}
