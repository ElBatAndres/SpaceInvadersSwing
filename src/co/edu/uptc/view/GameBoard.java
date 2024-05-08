package co.edu.uptc.view;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.pojos.CannonPojo;
import co.edu.uptc.utils.ComponentsConstants;
import co.edu.uptc.utils.ConfigManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    private MainBoard mainBoard;
    private CannonPojo cannon;
    private int shotKeyCode;
    private ArrayList<BulletPojo> bulletPojoList;
    private ArrayList<AlienPojo> alienPojoList;
    private boolean canShoot = true;
    private int currentKeyCode;
    private BufferedImage alienImage = null;
    private BufferedImage bulletImage = null;
    private BufferedImage cannonImage = null;

    public GameBoard(MainBoard mainBoard){
        setMainBoard(mainBoard);
        initPanel();
        initComponents();
    }

    private void initPanel(){
        this.setFocusable(true);
        addListener();
        this.setBackground(new Color(5, 75, 171));
    }

    private void addListener(){
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                currentKeyCode = e.getKeyCode();
                verifyCannonAction();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentKeyCode = e.getKeyCode();
                initShot(e);
            }
        });
    }

    private void verifyCannonAction(){
        mainBoard.presenter.updateCannon();
        repaint();
    }

    private void initShot(KeyEvent e) {
        if (e.getKeyCode() == shotKeyCode && canShoot) {
            int bulletSpawnTime = Integer.parseInt(ConfigManager.getGameProperty("bulletSpawnTime"));
            mainBoard.presenter.addBullet();
            canShoot = false;
            Timer timer = new Timer(bulletSpawnTime, event -> canShoot = true);
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void initComponents(){
        bulletPojoList = new ArrayList<>();
        alienPojoList = new ArrayList<>();
        cannon = mainBoard.presenter.createCannon();
        initImages();
    }

    public CannonPojo getCannon(){
        return cannon;
    }

    public void updateBulletsPosition(){
        bulletPojoList = mainBoard.presenter.getBulletsPosition();
        repaint();
    }

    public void updateAliensPosition(){
        alienPojoList = mainBoard.presenter.getAliensPosition();
        repaint();
    }

    private void initImages(){
        try {
            alienImage = ImageIO.read(new File(ConfigManager.getGameProperty("alienImagePath")));
            bulletImage = ImageIO.read(new File(ConfigManager.getGameProperty("bulletImagePath")));
            cannonImage = ImageIO.read(new File(ConfigManager.getGameProperty("cannonImagePath")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setShotKeyCode(int shotKeyCode) {
        this.shotKeyCode = shotKeyCode;
    }

    public int getCurrentKeyCode() {
        return currentKeyCode;
    }

    private void setMainBoard(MainBoard mainBoard){
        this.mainBoard = mainBoard;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cannonPosX = cannon.getPositionX();
        int cannonPosY = cannon.getPositionY();
        g.drawImage(cannonImage, cannonPosX, cannonPosY, ComponentsConstants.CANNON_IMAGE_WIDTH, ComponentsConstants.CANNON_IMAGE_HEIGHT, null);
        ArrayList<BulletPojo> copyBulletList = new ArrayList<>(bulletPojoList);
        for (BulletPojo bullet: copyBulletList){
            int bulletPosX = bullet.getPositionX();
            int bulletPosY = bullet.getPositionY();
            g.drawImage(bulletImage, bulletPosX, bulletPosY, ComponentsConstants.BULLET_IMAGE_WIDTH, ComponentsConstants.BULLET_IMAGE_HEIGHT, null);
        }
        ArrayList<AlienPojo> copyAlienList = new ArrayList<>(alienPojoList);
        for (AlienPojo alien : copyAlienList) {
            int alienPosX = alien.getPositionX();
            int alienPosY = alien.getPositionY();
            g.drawImage(alienImage, alienPosX, alienPosY, alien.getWidth(), alien.getHeight(), null);
        }
    }

}
