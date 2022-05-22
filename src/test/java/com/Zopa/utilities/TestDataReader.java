package com.Zopa.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class TestDataReader {

    private static Properties properties;

    //Helps us to retrieve some TestData from properties file like uri, endpoint, apigateway etc.

    static {

        try {
            String path = "src/test/resources/properties/TestData.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }
}
