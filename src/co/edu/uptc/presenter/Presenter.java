package co.edu.uptc.presenter;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.pojos.CannonPojo;
import co.edu.uptc.utils.ConfigManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Presenter implements ContractPlay.Presenter{

    private ContractPlay.View view;
    private ContractPlay.Model model;

    public void startTimer(){
        model.startTimer();
    }

    public void setPlayTime() {
        view.changeTime(model.getPlayTime());
    }

    public void startThreads(){
        model.createBulletThread();
        model.createAlienUpdateThread();
        model.createAlienAdditionThread();
    }

    public void updateBullets(){
        view.updateBullets();
    }

    public ArrayList<BulletPojo> getBulletsPosition(){
        return model.getBulletsList();
    }

    public void addBullet(){
        model.addBullet();
    }

    public BulletPojo getBulletInfo(){
        BulletPojo bullet = new BulletPojo();
        CannonPojo cannon = view.getCannon();
        bullet.setPositionX(cannon.getPositionX() + cannon.getWidth());
        bullet.setPositionY(cannon.getPositionY());
        bullet.setHeight(Integer.parseInt(ConfigManager.getProperty("bulletHeight")));
        bullet.setWidth(Integer.parseInt(ConfigManager.getProperty("bulletWidth")));
        bullet.setSpeed(Integer.parseInt(ConfigManager.getProperty("bulletSpeed")));
        return bullet;
    }

    public CannonPojo createCannon(){
        CannonPojo cannon = new CannonPojo();
        cannon.setSpeed(Integer.parseInt(ConfigManager.getProperty("cannonSpeed")));
        cannon.setPositionX((Integer.parseInt(ConfigManager.getProperty("windowWidth")) - 14)/2);
        cannon.setPositionY((Integer.parseInt(ConfigManager.getProperty("windowHeight")) - 64) - 110);
        cannon.setWidth(Integer.parseInt(ConfigManager.getProperty("cannonWidth")));
        cannon.setHeight(Integer.parseInt(ConfigManager.getProperty("cannonHeight")));
        return cannon;
    }

    public AlienPojo createRandomAlien(){
        AlienPojo alien = new AlienPojo();
        Random random = new Random();
        int alienMinSize = Integer.parseInt(ConfigManager.getProperty("alienMinSize"));
        int alienMaxSize = Integer.parseInt(ConfigManager.getProperty("alienMaxSize"));
        int alienMinSpeed = Integer.parseInt(ConfigManager.getProperty("alienMinSpeed"));
        int alienMaxSpeed = Integer.parseInt(ConfigManager.getProperty("alienMaxSpeed"));
        int randomSize = random.nextInt(alienMaxSize - alienMinSize + 1) + alienMinSize;
        boolean rightDirection = random.nextBoolean();
        alien.setRightDirection(rightDirection);
        alien.setWidth(randomSize);
        alien.setHeight(randomSize/2);
        if (rightDirection){
            alien.setPositionX(-1 * alien.getWidth());
        } else{
            alien.setPositionX(Integer.parseInt(ConfigManager.getProperty("windowWidth")));
        }
        alien.setPositionY(random.nextInt((Integer.parseInt(ConfigManager.getProperty("windowHeight"))-64)/2 - 10 + 1) + 10);
        alien.setSpeed(random.nextInt(alienMaxSpeed - alienMinSpeed + 1) + alienMinSpeed);
        return alien;
    }

    public void updateAliens() {
        view.updateAliens();
    }

    public ArrayList<AlienPojo> getAliensPosition(){
        return model.getAliensList();
    }

    public void updateCannon(){
        int currentKeyCode = view.getCurrentKeyCode();
        CannonPojo cannon = view.getCannon();
        int panelSize = Integer.parseInt(ConfigManager.getProperty("windowWidth")) -14;
        int currentCannonPosition = cannon.getPositionX();
        if (currentKeyCode == KeyEvent.VK_LEFT && currentCannonPosition - cannon.speed >= 0) {
            cannon.setPositionX(currentCannonPosition - cannon.speed);
        }
        if (currentKeyCode == KeyEvent.VK_RIGHT && currentCannonPosition + cannon.speed <= panelSize) {
            cannon.setPositionX(currentCannonPosition + cannon.speed);
        }
    }

    public int getAliensKilled(){
        return model.getAliensKilled();
    }

    public int getAliensOnScreen(){
        return model.getAliensOnGame();
    }

    @Override
    public void setView(ContractPlay.View view) {
        this.view = view;
    }

    public void setModel(ContractPlay.Model model){
        this.model = model;
    }

}
