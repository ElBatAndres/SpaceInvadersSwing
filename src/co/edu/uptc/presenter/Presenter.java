package co.edu.uptc.presenter;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.pojos.CannonPojo;
import co.edu.uptc.utils.ComponentsConstants;
import co.edu.uptc.utils.ConfigManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Presenter implements ContractPlay.Presenter{

    private ContractPlay.View view;
    private ContractPlay.Model model;

    public void startThreads(){
        model.createBulletThread();
        model.createAlienUpdateThread();
        model.createAlienAdditionThread();
    }

    public BulletPojo getBulletInfo(){
        BulletPojo bullet = new BulletPojo();
        CannonPojo cannon = view.getCannon();
        bullet.setPositionX(cannon.getPositionX() + cannon.getWidth());
        bullet.setPositionY(cannon.getPositionY());
        bullet.setHeight(Integer.parseInt(ConfigManager.getGameProperty("bulletHeight")));
        bullet.setWidth(Integer.parseInt(ConfigManager.getGameProperty("bulletWidth")));
        bullet.setSpeed(Integer.parseInt(ConfigManager.getGameProperty("bulletSpeed")));
        return bullet;
    }

    public CannonPojo createCannon(){
        CannonPojo cannon = new CannonPojo();
        cannon.setPositionX((ComponentsConstants.WINDOW_WIDTH - 14)/2);
        cannon.setPositionY((ComponentsConstants.WINDOW_HEIGHT - 64) - 110);
        cannon.setSpeed(Integer.parseInt(ConfigManager.getGameProperty("cannonSpeed")));
        cannon.setWidth(Integer.parseInt(ConfigManager.getGameProperty("cannonWidth")));
        cannon.setHeight(Integer.parseInt(ConfigManager.getGameProperty("cannonHeight")));
        return cannon;
    }

    public AlienPojo createRandomAlien(){
        AlienPojo alien = new AlienPojo();
        Random random = new Random();
        int alienMinSize = Integer.parseInt(ConfigManager.getGameProperty("alienMinSize"));
        int alienMaxSize = Integer.parseInt(ConfigManager.getGameProperty("alienMaxSize"));
        int alienMinSpeed = Integer.parseInt(ConfigManager.getGameProperty("alienMinSpeed"));
        int alienMaxSpeed = Integer.parseInt(ConfigManager.getGameProperty("alienMaxSpeed"));
        int size = random.nextInt(alienMaxSize - alienMinSize + 1) + alienMinSize;
        boolean rightDirection = random.nextBoolean();
        alien.setRightDirection(rightDirection);
        alien.setWidth(size);
        alien.setHeight(size/2);
        if (rightDirection){
            alien.setPositionX(-1 * alien.getWidth());
        } else{
            alien.setPositionX(ComponentsConstants.WINDOW_WIDTH);
        }
        alien.setPositionY(random.nextInt((ComponentsConstants.WINDOW_HEIGHT - 64)/2 - 10 + 1) + 10);
        alien.setSpeed(random.nextInt(alienMaxSpeed - alienMinSpeed + 1) + alienMinSpeed);
        return alien;
    }

    public void updateCannon(){
        int currentKeyCode = view.getCurrentKeyCode();
        CannonPojo cannon = view.getCannon();
        int panelSize = ComponentsConstants.WINDOW_WIDTH -14;
        int currentCannonPosition = cannon.getPositionX();
        if (currentKeyCode == KeyEvent.VK_LEFT && currentCannonPosition - cannon.speed >= 0) {
            cannon.setPositionX(currentCannonPosition - cannon.speed);
        }
        if (currentKeyCode == KeyEvent.VK_RIGHT && currentCannonPosition + cannon.speed <= panelSize) {
            cannon.setPositionX(currentCannonPosition + cannon.speed);
        }
    }

    public void updateAliens() {
        view.updateAliens();
    }

    public ArrayList<AlienPojo> getAliensPosition(){
        return model.getAliensList();
    }

    public void startTimer(){
        model.startTimer();
    }

    public void setPlayTime() {
        view.changeTime(model.getPlayTime());
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
