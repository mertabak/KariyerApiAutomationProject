package Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    // Static block to load the properties file when the class is loaded
    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("TestData.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find TestData.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to get property values by key from the properties file
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
