package co.edu.uptc.view;

import co.edu.uptc.utils.ConfigManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyConfirmationDialog extends JDialog {

    private MainView mainView;
    private JLabel keyRequestLbl;
    private JButton keyConfirmationBtn;
    private JLabel keyConfirmationLbl;
    private int keyCode;

    public KeyConfirmationDialog(MainView mainView){
        super(mainView, true);
        this.mainView = mainView;
        initDialog();
        initComponents();
        this.setVisible(true);
    }

    private void initDialog() {
        this.setLayout(new GridBagLayout());
        this.setTitle("Confirmacion");
        this.setSize(Integer.parseInt(ConfigManager.getProperty("confirmationDialogWidth")),Integer.parseInt(ConfigManager.getProperty("confirmationDialogHeight")));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(69, 129, 218));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addKeyListeners();
    }

    private void initComponents(){
        keyRequestLbl = new JLabel("Ingrese una tecla para disparar");
        keyRequestLbl.setFont(new Font("", Font.BOLD, 20));
        keyRequestLbl.setForeground(new Color(0, 0, 0));
        keyConfirmationBtn = new JButton("Hecho"){
            @Override
            protected void paintComponent(Graphics g) {
                g.fillRect(0,0,getWidth(),getHeight());
                g.setColor(new Color(255,255,255));
                g.fillRect(2,2,getWidth()-4,getHeight()-4);
                g.setColor(new Color(33, 33, 33));
                g.drawString(getText(),getWidth()-80,(getHeight())-10);
            }
        };
        keyConfirmationBtn.setFocusPainted(false);
        keyConfirmationBtn.setBorderPainted(false);
        keyConfirmationBtn.setForeground(new Color(0,0,0));
        keyConfirmationBtn.setFont(new Font("", Font.BOLD, 20));
        keyConfirmationBtn.addActionListener(e -> {
            if (keyCode != 0){
                mainView.setShotKeyCode(keyCode);
                dispose();
            }
        });
        keyConfirmationBtn.setEnabled(false);
        keyConfirmationLbl = new JLabel("\u200E");
        keyConfirmationLbl.setFont(new Font("", Font.BOLD, 20));
        keyConfirmationLbl.setForeground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,0,5,0);
        gbc.gridy = 0;
        this.add(keyRequestLbl,gbc);

        gbc.gridy = 1;
        this.add(keyConfirmationLbl,gbc);

        gbc.gridy = 2;
        this.add(keyConfirmationBtn,gbc);
    }

    private void addKeyListeners(){
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyConfirmationBtn.setEnabled(true);
                keyCode = e.getKeyCode();
                keyConfirmationLbl.setText(KeyEvent.getKeyText(keyCode));
            }
        });
    }
}
