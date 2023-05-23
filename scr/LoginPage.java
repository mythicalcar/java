package scr;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LoginPage extends JFrame {
    Database db = new Database();
    JTextField userName = new JTextField();
    JPasswordField userPass = new JPasswordField();
    JLabel userNameTxt = new JLabel("Naam:");
    JLabel userPassTxt = new JLabel("Wachtwoord:");
    JLabel errFlash = new JLabel("Verkeerde wachtwoord of gebruikersnaam, probeer opnieuw");
    JButton login = new JButton("Login");

    LoginPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.setBackground(new Color(120, 120, 120));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        login.setBounds(200, 300, 100, 40);
        login.setFocusable(false);
        login.addActionListener(e -> {
            String name = userName.getText();
            String password = String.valueOf(userPass.getPassword());
            int validation = db.checkUserData(name, password);
            if (validation == 1) {
                this.dispose();
                new BezorgerPage(name, db);

            } else if (validation == 2) {
                this.dispose();
                new ManagerPage(name, db);
            } else {
                errFlash.setVisible(true);
            }
        });

        userName.setBounds(125, 130, 250, 40);
        userPass.setBounds(125, 230, 250, 40);
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userPass.setFont(new Font("Arial", Font.PLAIN, 20));
        userNameTxt.setBounds(125, 90, 250, 40);
        userPassTxt.setBounds(125, 190, 250, 40);
        errFlash.setBounds(155, 420, 250, 40);
        errFlash.setForeground(Color.red);
        errFlash.setVisible(false);

        this.add(login);
        this.add(userName);
        this.add(userNameTxt);
        this.add(userPass);
        this.add(userPassTxt);
        this.add(errFlash);
    }
}
