package co.edu.uptc.model;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.utils.Chronometer;
import co.edu.uptc.utils.ConfigManager;
import co.edu.uptc.utils.SleepThread;

import java.awt.*;
import java.util.*;

public class GameManagement implements ContractPlay.Model {
    protected ContractPlay.Presenter presenter;
    private BulletManager bulletManager;
    private AlienManager alienManager;
    private Chronometer chronometer;
    private int aliensKilled;
    private int aliensSpawned;

    public GameManagement(){
        bulletManager = new BulletManager();
        alienManager = new AlienManager();
    }

    public void startTimer(){
        int period = Integer.parseInt(ConfigManager.getGameProperty("timerPeriod"));
        chronometer = new Chronometer();
        chronometer.getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                chronometer.increaseSecond();
                presenter.setPlayTime();
            }
        }, 0, period);
    }

    public void createBulletThread(){
        int refresh = Integer.parseInt(ConfigManager.getGameProperty("refreshBulletTime"));
        Thread bulletThread = new Thread(() -> {
            while (true){
                SleepThread.sleep(refresh);
                bulletManager.updateBulletsPosition();
                presenter.updateBullets();
            }
        });
        bulletThread.start();
    }

    public synchronized void verifyStrikeByElements() {
        ArrayList<BulletPojo> bulletList = bulletManager.getBulletPojoList();
        ArrayList<AlienPojo> alienList = alienManager.getAlienPojoList();
        Iterator<BulletPojo> bulletIterator = bulletList.iterator();
        while (bulletIterator.hasNext()) {
            BulletPojo bullet = bulletIterator.next();
            Iterator<AlienPojo> alienIterator = alienList.iterator();
            while (alienIterator.hasNext()) {
                AlienPojo alien = alienIterator.next();
                if (verifyCollision(alien, bullet)) {
                    bulletIterator.remove();
                    alienIterator.remove();
                    aliensKilled++;
                }
            }
        }
        bulletManager.setBulletPojoList(bulletList);
        alienManager.setAlienPojoList(alienList);
    }

    private boolean verifyCollision(AlienPojo alien, BulletPojo bulletPojo) {
        Rectangle alienRect = new Rectangle(alien.getPositionX(), alien.getPositionY(), alien.getWidth(), alien.getHeight());
        Rectangle bulletRect = new Rectangle(bulletPojo.getPositionX(), bulletPojo.getPositionY(), bulletPojo.getWidth(), bulletPojo.getHeight());
        return alienRect.intersects(bulletRect);
    }

    public void createAlienUpdateThread(){
        int refresh = Integer.parseInt(ConfigManager.getGameProperty("refreshAliensTime"));
        Thread alienUpdateThread = new Thread(() -> {
            while (true){
                SleepThread.sleep(refresh);
                updateAllAliensPosition();
                presenter.updateAliens();
            }
        });
        alienUpdateThread.start();
    }

    public void createAlienAdditionThread(){
        Random random = new Random();
        int alienSpawnMaxTime = Integer.parseInt(ConfigManager.getGameProperty("alienSpawnMaxTime"));
        int alienSpawnMinTime = Integer.parseInt(ConfigManager.getGameProperty("alienSpawnMinTime"));
        Thread alienAddThread = new Thread(() -> {
            while (true){
                SleepThread.sleep(random.nextInt(alienSpawnMaxTime - alienSpawnMinTime + 1) + alienSpawnMinTime);
                addNewAlien();
            }
        });
        alienAddThread.start();
    }

    public String getPlayTime(){
        return chronometer.getTime();
    }

    public synchronized void addBullet(){
        bulletManager.addBullet(presenter.getBulletInfo());
    }

    public synchronized ArrayList<BulletPojo> getBulletsList(){
        bulletManager.verifyBulletsInScreen();
        verifyStrikeByElements();
        return bulletManager.getBulletPojoList();
    }

    public synchronized ArrayList<AlienPojo> getAliensList(){
        alienManager.verifyAliensInScreen();
        return alienManager.getAlienPojoList();
    }

    public synchronized void updateAllAliensPosition(){
        alienManager.updateAliensPosition();
    }

    private synchronized void addNewAlien(){
        aliensSpawned++;
        alienManager.addAlien(presenter.createRandomAlien());
    }

    public int getAliensKilled() {
        return aliensKilled;
    }

    public int getAliensOnGame() {
        return aliensSpawned;
    }

    @Override
    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = presenter;
    }

}
