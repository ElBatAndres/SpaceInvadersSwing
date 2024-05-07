package co.edu.uptc.utils;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    public ConfigManager(){
        initProperties();
    }

    private void initProperties() {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
