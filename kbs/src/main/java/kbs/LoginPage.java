package kbs;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LoginPage extends JPanel {
    Database db = new Database();
    JTextField userName = new JTextField();
    JPasswordField userPass = new JPasswordField();
    JLabel userNameTxt = new JLabel("Name:");
    JLabel userPassTxt = new JLabel("Password:");
    JLabel errFlash = new JLabel("Wrong name or password, try again");
    JButton login = new JButton("Login");

    LoginPage(StartPage frame) {
        this.setBounds(0, 0, 500, 500);
        this.setFocusable(true);
        this.setBackground(new Color(120, 120, 120));
        this.setOpaque(true);
        this.setLayout(null);

        login.setBounds(200, 300, 100, 40);
        login.setFocusable(false);
        login.addActionListener(e -> {
            String name = userName.getText();
            String password = String.valueOf(userPass.getPassword());
            Boolean validation = db.checkUserData(name, password);
            if (validation) {
                frame.dispose();
                new HomePage(name, db);
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
