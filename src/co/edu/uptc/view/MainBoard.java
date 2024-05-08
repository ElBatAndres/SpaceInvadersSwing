package co.edu.uptc.view;

import co.edu.uptc.pojos.CannonPojo;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.utils.ComponentsConstants;

import javax.swing.*;
import java.awt.*;

public class MainBoard extends JFrame implements ContractPlay.View {

    protected ContractPlay.Presenter presenter;
    private ScoreBoard scorePanel;
    private GameBoard gamePanel;

    public MainBoard(){
        initFrame();
    }

    public void initComponents(){
        createGamePanel();
        createDialog();
        createScorePanel();

        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.getContentPane().add(scorePanel, BorderLayout.NORTH);
    }

    public void createScorePanel(){
        scorePanel = new ScoreBoard(this);
        presenter.startTimer();
    }

    public void createGamePanel(){
        gamePanel = new GameBoard(this);
    }

    public void createDialog(){
        new KeyConfirmationDialog(this);
    }

    public void initFrame(){
        this.setTitle("Space Invaders");
        this.setSize(ComponentsConstants.WINDOW_WIDTH, ComponentsConstants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    public void begin(){
        initComponents();
        presenter.startThreads();
        this.setVisible(true);
    }

    @Override
    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = presenter;
    }

    public CannonPojo getCannon(){
        return gamePanel.getCannon();
    }

    @Override
    public int getCurrentKeyCode() {
        return gamePanel.getCurrentKeyCode();
    }

    public void updateAliens(){
        scorePanel.setAlienOnScreenText();
        scorePanel.setAlienKilledText();
        gamePanel.updateAliensPosition();
    }

    public void changeTime(String playTime){
        scorePanel.setTimeText(playTime);
    }

    public void setShotKeyCode(int keyCode){
        gamePanel.setShotKeyCode(keyCode);
    }

    public void updateBullets(){
        gamePanel.updateBulletsPosition();
    }

}
