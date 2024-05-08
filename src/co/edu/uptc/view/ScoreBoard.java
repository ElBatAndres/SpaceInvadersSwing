package co.edu.uptc.view;

import co.edu.uptc.utils.ComponentsConstants;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    private MainBoard mainView;
    private JLabel timeLbl;
    private JLabel alienKilledLbl;
    private JLabel alienOnScreenLbl;

    public ScoreBoard(MainBoard mainView){
        setMainView(mainView);
        initPanel();
        initComponents();
    }

    public void initPanel(){
        setLayout(new GridBagLayout());
        this.setBackground(new Color(69, 129, 218));
    }

    private void initComponents() {
        timeLbl = new JLabel("<html>" + ComponentsConstants.TIME_PLAYED_MESSAGE + ": <strong style='color:rgb(255, 255, 255);'>00:00:00</strong></html>");
        timeLbl.setFont(new Font("", Font.PLAIN, 20));
        alienKilledLbl = new JLabel("<html>" + ComponentsConstants.ALIENS_KILLED_MESSAGE + ": <strong style='color:rgb(15, 75, 38);'>" + mainView.presenter.getAliensKilled() + "</strong></html>");
        alienKilledLbl.setFont(new Font("", Font.PLAIN, 20));
        alienOnScreenLbl = new JLabel("<html>" + ComponentsConstants.ALIENS_NUMBER_MESSAGE + ": <strong style='color:red;'>" + mainView.presenter.getAliensOnScreen() + "</strong></html>");
        alienOnScreenLbl.setFont(new Font("", Font.PLAIN, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0,10,0,10);
        gbc.gridx = 0;
        this.add(timeLbl, gbc);

        gbc.gridx = 1;
        this.add(alienOnScreenLbl, gbc);

        gbc.gridx = 2;
        this.add(alienKilledLbl, gbc);
    }

    public void setAlienOnScreenText(){
        alienOnScreenLbl.setText("<html>" + ComponentsConstants.ALIENS_NUMBER_MESSAGE + ": <strong style='color:red;'>" + mainView.presenter.getAliensOnScreen() + "</strong></html>");
    }

    public void setAlienKilledText(){
        alienKilledLbl.setText("<html>" + ComponentsConstants.ALIENS_KILLED_MESSAGE + ": <strong style='color:rgb(255, 255, 255);'>" + mainView.presenter.getAliensKilled() + "</strong></html>");
    }

    public void setTimeText(String time){
        timeLbl.setText("<html>" + ComponentsConstants.TIME_PLAYED_MESSAGE + ": <strong style='color:rgb(255, 255, 255);'>" + time + "</strong></html>");
    }

    private void setMainView(MainBoard mainView){
        this.mainView = mainView;
    }
}
