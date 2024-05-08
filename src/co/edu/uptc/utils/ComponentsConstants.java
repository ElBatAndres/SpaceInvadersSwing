package co.edu.uptc.utils;

public class ComponentsConstants {
        public static final int CANNON_IMAGE_WIDTH = Integer.parseInt(ConfigManager.getGameProperty("cannonImageWidth"));
        public static final int CANNON_IMAGE_HEIGHT = Integer.parseInt(ConfigManager.getGameProperty("cannonImageHeight"));
        public static final int BULLET_IMAGE_WIDTH = Integer.parseInt(ConfigManager.getGameProperty("bulletImageWidth"));
        public static final int BULLET_IMAGE_HEIGHT = Integer.parseInt(ConfigManager.getGameProperty("bulletImageHeight"));
        public static final int WINDOW_WIDTH = Integer.parseInt(ConfigManager.getGameProperty("windowWidth"));
        public static final int WINDOW_HEIGHT = Integer.parseInt(ConfigManager.getGameProperty("windowHeight"));
        public static final String TIME_PLAYED_MESSAGE = ConfigManager.getIdiomProperty("timePlayedMsg");
        public static final String ALIENS_KILLED_MESSAGE = ConfigManager.getIdiomProperty("aliensKilledMsg");
        public static final String ALIENS_NUMBER_MESSAGE = ConfigManager.getIdiomProperty("aliensNumberMsg");

}
