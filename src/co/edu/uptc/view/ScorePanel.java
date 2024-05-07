package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private MainView mainView;
    private JLabel timeLbl;
    private JLabel alienKilledLbl;
    private JLabel alienOnScreenLbl;

    public ScorePanel(MainView mainView){
        setMainView(mainView);
        initPanel();
        initComponents();
    }

    public void initPanel(){
        setLayout(new GridBagLayout());
        this.setBackground(new Color(69, 129, 218));
    }

    private void initComponents() {
        timeLbl = new JLabel("<html>Tiempo jugado: <strong style='color:rgb(255, 255, 255);'>00:00:00</strong></html>");
        timeLbl.setFont(new Font("", Font.PLAIN, 20));
        alienKilledLbl = new JLabel("<html>Aliens aniquilados: <strong style='color:rgb(15, 75, 38);'>" + mainView.presenter.getAliensKilled() + "</strong></html>");
        alienKilledLbl.setFont(new Font("", Font.PLAIN, 20));
        alienOnScreenLbl = new JLabel("<html>Aliens en pantalla: <strong style='color:red;'>" + mainView.presenter.getAliensOnScreen() + "</strong></html>");
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
        alienOnScreenLbl.setText("<html>Aliens en pantalla: <strong style='color:red;'>" + mainView.presenter.getAliensOnScreen() + "</strong></html>");
    }

    public void setAlienKilledText(){
        alienKilledLbl.setText("<html>Aliens aniquilados: <strong style='color:rgb(255, 255, 255);'>" + mainView.presenter.getAliensKilled() + "</strong></html>");
    }

    public void setTimeText(String time){
        timeLbl.setText("<html>Tiempo jugado: <strong style='color:rgb(255, 255, 255);'>" + time + "</strong></html>");
    }



    private void setMainView(MainView mainView){
        this.mainView = mainView;
    }
}
