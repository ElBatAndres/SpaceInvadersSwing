package co.edu.uptc.view;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.pojos.CannonPojo;
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

public class GamePanel extends JPanel {

    private MainView mainView;
    private CannonPojo cannon;
    private int shotKeyCode;
    private int cannonImageWidth;
    private int cannonImageHeight;
    private int bulletImageWidth;
    private int bulletImageHeight;
    private ArrayList<BulletPojo> bulletPojoList;
    private ArrayList<AlienPojo> alienPojoList;
    private boolean canShoot = true;
    private int currentKeyCode;
    private BufferedImage alienImage = null;
    private BufferedImage bulletImage = null;
    private BufferedImage cannonImage = null;

    public GamePanel(MainView mainView){
        this.mainView = mainView;
        initPanel();
        initComponents();
    }

    public void initPanel(){
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                currentKeyCode = e.getKeyCode();
                verifyCannonAction(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentKeyCode = e.getKeyCode();
                initShot(e);
            }
        });
        this.setBackground(new Color(5, 75, 171));
    }

    public void verifyCannonAction(KeyEvent e){
        mainView.presenter.updateCannon();
        repaint();
    }

    public void initShot(KeyEvent e) {
        if (e.getKeyCode() == shotKeyCode && canShoot) {
            mainView.presenter.addBullet();
            canShoot = false;
            Timer timer = new Timer(Integer.parseInt(ConfigManager.getProperty("bulletSpawnTime")), event -> canShoot = true);
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void initComponents(){
        bulletPojoList = new ArrayList<>();
        alienPojoList = new ArrayList<>();
        cannonImageWidth = Integer.parseInt(ConfigManager.getProperty("cannonImageWidth"));
        cannonImageHeight = Integer.parseInt(ConfigManager.getProperty("cannonImageHeight"));
        bulletImageWidth = Integer.parseInt(ConfigManager.getProperty("bulletImageWidth"));
        bulletImageHeight = Integer.parseInt(ConfigManager.getProperty("bulletImageHeight"));
        cannon = mainView.presenter.createCannon();
        initImages();
    }

    public CannonPojo getCannon(){
        return cannon;
    }

    public void updateBulletsPosition(){
        bulletPojoList = mainView.presenter.getBulletsPosition();
        repaint();
    }

    public void updateAliensPosition(){
        alienPojoList = mainView.presenter.getAliensPosition();
        repaint();
    }

    public void initImages(){
        try {
            alienImage = ImageIO.read(new File(ConfigManager.getProperty("alienImagePath")));
            bulletImage = ImageIO.read(new File(ConfigManager.getProperty("bulletImagePath")));
            cannonImage = ImageIO.read(new File(ConfigManager.getProperty("cannonImagePath")));
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(cannonImage, cannon.getPositionX(), cannon.getPositionY(), cannonImageWidth, cannonImageHeight, null);
        for (BulletPojo bullet: bulletPojoList){
            g.drawImage(bulletImage, bullet.getPositionX(), bullet.getPositionY(), bulletImageWidth, bulletImageHeight, null);
        }
        ArrayList<AlienPojo> copyList = new ArrayList<>(alienPojoList);
        for (AlienPojo alien : copyList) {
            g.drawImage(alienImage, alien.getPositionX(), alien.getPositionY(), alien.getWidth(), alien.getHeight(), null);
        }
    }

}
