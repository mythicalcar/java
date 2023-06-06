package scr;

import com.mongodb.BasicDBObject;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    Database db;
    JTextField userName = new JTextField();
    JPasswordField userPass = new JPasswordField();
    JLabel userNameTxt = new JLabel("Gebruikersaam:");
    JLabel userPassTxt = new JLabel("Wachtwoord:");
    JLabel errFlash = new JLabel("Verkeerde wachtwoord of gebruikersnaam, probeer opnieuw");
    JButton login = new JButton("Log in");
    JPanel errFlashPanel = new JPanel(new BorderLayout());
    ApplicationFrame applicationFrame;
    JPanel centerPanel = new JPanel();
    public LoginPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        db = applicationFrame.getDb();
        this.setLayout(new BorderLayout());

        login.addActionListener(e -> {
            String name = userName.getText();
            String password = String.valueOf(userPass.getPassword());
            int validation = db.checkUserData(name, password);
            if (validation == 1) {
                applicationFrame.setUserName(name);
                //not hashing the user id might not be the most secure, but that is something that unfortunately does not fall within our budget at this point in time
                applicationFrame.showBezorgerPage();
                applicationFrame.setBezorgerId(applicationFrame.getDb().getBezorgerId(name));
                System.out.println(applicationFrame.getBezorgerId());
                applicationFrame.setBestellingen(applicationFrame.getDb().getBestellingenFromBezorger(applicationFrame.getBezorgerId()));
                System.out.println(applicationFrame.getBestellingen());
                //for (Bestelling value : applicationFrame.getBestellingen()){
                  //  System.out.println(value);
                //}
            } else if (validation == 2) {
                applicationFrame.setUserName(name);
                applicationFrame.showManagerPage();
            } else {
                errFlash.setVisible(true);
            }
        });


        errFlash.setForeground(Color.red);
        errFlash.setVisible(false);
        errFlash.setHorizontalAlignment(JLabel.CENTER);
        errFlashPanel.setPreferredSize(errFlash.getPreferredSize());
        errFlashPanel.add(errFlash, BorderLayout.PAGE_START);

        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(userNameTxt, ApplicationFrame.createGBC(0, 0, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userName, ApplicationFrame.createGBC(0, 1, 3, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userPassTxt, ApplicationFrame.createGBC(0, 2, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(userPass, ApplicationFrame.createGBC(0, 3, 3, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));
        centerPanel.add(login, ApplicationFrame.createGBC(0, 5, 1, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL));

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(errFlashPanel, BorderLayout.PAGE_END);

    }
}