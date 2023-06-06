package scr;

import org.intellij.lang.annotations.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JPanel implements ActionListener {
    Database db;// = new Database();
    JTextField userName = new JTextField();
    JPasswordField userPass = new JPasswordField();
    JLabel userNameTxt = new JLabel("Gebruikersnaam:");
    JLabel userPassTxt = new JLabel("Wachtwoord:");
    JPanel flashPanel = new JPanel(new BorderLayout());
    JLabel errFlash = new JLabel("Bezorgeraccount met deze gebruikersnaam bestaat al");
    JLabel successFlash = new JLabel("Bezorgeracount successvol aangemaakt");
    JButton register = new JButton("Register");
    JButton backButton = new JButton("Terug");
    JPanel backButtonPanel = new JPanel();
    ApplicationFrame applicationFrame;
    JLabel menuTitleLabel = new JLabel("Bezorger registreren");
    JPanel centerPanel = new JPanel(new GridBagLayout());
    JPanel pageEndPanel = new JPanel(new BorderLayout());
    RegisterPage(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
//        this.setBounds(0, 0, 500, 500);
//        this.setFocusable(true);
//        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        db = applicationFrame.getDb();

        centerPanel.setLayout(new GridBagLayout());
//        register.setBounds(200, 300, 100, 40);
//        register.setFocusable(false);
        register.addActionListener(e -> {
            String name = userName.getText();
            String password = String.valueOf(userPass.getPassword());
            Boolean created = db.createAccount(name, password);
            if (created) {
                //Success message: successflash?
//                this.setVisible(false);
//                startPage.setVisible(true);
                flashPanel.removeAll();
                flashPanel.add(successFlash);
                flashPanel.revalidate();
                flashPanel.repaint();
                applicationFrame.refreshBezorgerAndRouteMenuPage();
            } else {
                flashPanel.removeAll();
                flashPanel.add(errFlash);
                flashPanel.revalidate();
                flashPanel.repaint();
            }
        });

        backButton.addActionListener(this);

        errFlash.setForeground(Color.red);
//        errFlash.setVisible(false);
        errFlash.setHorizontalAlignment(JLabel.CENTER);

        successFlash.setForeground(Color.green);
        successFlash.setHorizontalAlignment(JLabel.CENTER);

        flashPanel.setPreferredSize(errFlash.getPreferredSize());
//        flashPanel.add(errFlash, BorderLayout.PAGE_START);

        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(userNameTxt, ApplicationFrame.createGBC(0, 0, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userName, ApplicationFrame.createGBC(0, 1, 3, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userPassTxt, ApplicationFrame.createGBC(0, 2, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userPass, ApplicationFrame.createGBC(0, 3, 3, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(register, ApplicationFrame.createGBC(0, 5, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));

        backButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);

        pageEndPanel.setLayout(new GridLayout(0, 1));
        pageEndPanel.add(flashPanel);
        pageEndPanel.add(backButtonPanel);

        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            flashPanel.removeAll();
            applicationFrame.showBezorgerMenuPage();
        }
    }
}
