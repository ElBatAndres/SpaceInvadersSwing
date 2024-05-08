package co.edu.uptc.utils;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties gameProperties = new Properties();
    private static final Properties idiomProperties = new Properties();

    public ConfigManager(){
        initProperties();
    }

    private void initProperties() {
        InputStream inputStreamGameProperties = Main.class.getClassLoader().getResourceAsStream("config.properties");
        InputStream inputStreamIdiomProperties = Main.class.getClassLoader().getResourceAsStream("IdiomConfig.properties");
        try {
            gameProperties.load(inputStreamGameProperties);
            idiomProperties.load(inputStreamIdiomProperties);

            if (inputStreamGameProperties != null) {
                inputStreamGameProperties.close();
            }
            if (inputStreamIdiomProperties != null) {
                inputStreamIdiomProperties.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGameProperty(String key) {
        return gameProperties.getProperty(key);
    }

    public static String getIdiomProperty(String key) {
        return idiomProperties.getProperty(key);
    }

}
