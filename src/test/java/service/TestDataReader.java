package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static int getTestData(String key){
        return Integer.parseInt(resourceBundle.getString(key));
    }
}
