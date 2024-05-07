package co.edu.uptc.presenter;

import co.edu.uptc.pojos.AlienPojo;
import co.edu.uptc.pojos.BulletPojo;
import co.edu.uptc.pojos.CannonPojo;

import java.util.ArrayList;

public interface ContractPlay {

    public interface Presenter {
        void startTimer();
        void setPlayTime();
        void startThreads();
        void updateBullets();
        ArrayList<BulletPojo> getBulletsPosition();
        void addBullet();
        BulletPojo getBulletInfo();
        CannonPojo createCannon();
        AlienPojo createRandomAlien();
        void updateAliens();
        ArrayList<AlienPojo> getAliensPosition();
        void updateCannon();
        int getAliensKilled();
        int getAliensOnScreen();
        void setView(View view);
        void setModel(Model model);
    }

    public interface Model {
        void startTimer();
        String getPlayTime();
        void createBulletThread();
        void addBullet();
        ArrayList<BulletPojo> getBulletsList();
        void verifyStrikeByElements();
        void createAlienUpdateThread();
        ArrayList<AlienPojo> getAliensList();
        void updateAllAliensPosition();
        void createAlienAdditionThread();
        int getAliensKilled();
        int getAliensOnGame();
        void setPresenter(Presenter presenter);
    }

    public interface View {
        void initComponents();
        void createScorePanel();
        void createGamePanel();
        void createDialog();
        void initFrame();
        void begin();
        CannonPojo getCannon();
        void updateBullets();
        int getCurrentKeyCode();
        void updateAliens();
        void changeTime(String playTime);
        void setShotKeyCode(int keyCode);
        void setPresenter(Presenter presenter);
    }


}
