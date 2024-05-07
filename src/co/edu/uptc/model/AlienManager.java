package co.edu.uptc.model;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.utils.ConfigManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AlienManager {

    private ArrayList<AlienPojo> alienPojoList;
    private List<AlienPojo> alienPojosList = Collections.synchronizedList(new ArrayList<>());

    public AlienManager(){
        alienPojoList = new ArrayList<>();
    }

    public  void updateAliensPosition(){
        synchronized (alienPojoList) {
            for (AlienPojo alien : alienPojoList) {
                if (alien.isRightDirection()) {
                    alien.setPositionX(alien.getPositionX() + alien.getSpeed());
                } else {
                    alien.setPositionX(alien.getPositionX() - alien.getSpeed());
                }
            }
        }
    }

    public void addAlien(AlienPojo alien) {
        synchronized (alienPojoList) {
            alienPojoList.add(alien);
        }
    }

    public void verifyAliensInScreen() {
        synchronized (alienPojoList) {
            int windowWidth = Integer.parseInt(ConfigManager.getProperty("windowWidth"));
            Iterator<AlienPojo> iterator = alienPojoList.iterator();
            while (iterator.hasNext()) {
                AlienPojo alien = iterator.next();
                if (alien.getPositionX() > windowWidth || alien.getPositionX() < -1 * alien.getWidth()) {
                    iterator.remove();
                }
            }
        }
    }

    public ArrayList<AlienPojo> getAlienPojoList() {
        return alienPojoList;
    }

    public void setAlienPojoList(ArrayList<AlienPojo> alienPojoList) {
        this.alienPojoList = alienPojoList;
    }
}
