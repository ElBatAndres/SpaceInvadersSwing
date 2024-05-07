package co.edu.uptc.utils;

import java.util.Timer;

public class Chronometer {
    private Timer timer;
    private int seconds;

    public Chronometer() {
        timer = new Timer();
        seconds = 0;
    }

    public String getTime() {
        int hour = seconds / 3600;
        int min = (seconds % 3600) / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    public Timer getTimer() {
        return timer;
    }

    public void increaseSecond(){
        seconds++;
    }
}