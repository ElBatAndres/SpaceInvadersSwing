package co.edu.uptc.model;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.utils.ComponentsConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AlienManager {

    private ArrayList<AlienPojo> alienPojoList;
    private final List<AlienPojo> alienPojoSync = Collections.synchronizedList(new ArrayList<>());

    public AlienManager(){
        alienPojoList = new ArrayList<>();
    }

    public  void updateAliensPosition(){
        synchronized (alienPojoSync) {
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
        synchronized (alienPojoSync) {
            alienPojoList.add(alien);
        }
    }

    public void verifyAliensInScreen() {
        synchronized (alienPojoSync) {
            Iterator<AlienPojo> iterator = alienPojoList.iterator();
            while (iterator.hasNext()) {
                AlienPojo alien = iterator.next();
                if (alien.getPositionX() > ComponentsConstants.WINDOW_WIDTH || alien.getPositionX() < -1 * alien.getWidth()) {
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
