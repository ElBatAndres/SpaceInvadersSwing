package co.edu.uptc.model;

import co.edu.uptc.pojos.BulletPojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BulletManager {

    private ArrayList<BulletPojo> bulletPojoList;
    private final List<BulletPojo> bulletPojoSync = Collections.synchronizedList(new ArrayList<>());

    public BulletManager(){
        bulletPojoList = new ArrayList<>();
    }

    public void addBullet(BulletPojo bulletPojo){
        synchronized (bulletPojoSync) {
            bulletPojoList.add(bulletPojo);
        }
    }

    public void updateBulletsPosition(){
        synchronized (bulletPojoSync) {
            for (BulletPojo bullet : bulletPojoList) {
                bullet.setPositionY(bullet.getPositionY() - bullet.getSpeed());
            }
        }
    }

    public void verifyBulletsInScreen() {
        synchronized (bulletPojoSync) {
            Iterator<BulletPojo> iterator = bulletPojoList.iterator();
            while (iterator.hasNext()) {
                BulletPojo bullet = iterator.next();
                if (bullet.getPositionY() < 0) {
                    iterator.remove();
                }
            }
        }
    }

    public ArrayList<BulletPojo> getBulletPojoList() {
        return bulletPojoList;
    }

    public void setBulletPojoList(ArrayList<BulletPojo> bulletPojoList) {
        this.bulletPojoList = bulletPojoList;
    }
}
