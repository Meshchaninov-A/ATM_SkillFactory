package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class LoadProperties {
    private static final String SERVICE_PROPERTIES_FILE = "/service.properties";

    static Properties getServiceProperties() {
        return getPropertyFromFile(SERVICE_PROPERTIES_FILE);
    }

    private static Properties getPropertyFromFile(String fileName) {
        Properties properties = null;
        try {
            InputStream inputStream = LoadProperties.class.getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            properties = new Properties();
            properties.load(inputStreamReader);
        } catch (IOException e) {
            System.out.println("Error load properties");
        }
        return properties;
    }
}
