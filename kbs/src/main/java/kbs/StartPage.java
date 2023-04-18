package kbs;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StartPage extends JFrame {
    // Database db = new Database();
    JPanel startPagePanel = new JPanel();
    JLabel startPageLabel = new JLabel(
            "Welcome to this notepad application! Make an account and login to use it");
    JButton register = new JButton("Register");
    JButton login = new JButton("Login");
    RegisterPage registerPage = new RegisterPage(startPagePanel);
    LoginPage loginPage = new LoginPage(this);

    StartPage() {
        registerPage.setVisible(false);
        loginPage.setVisible(false);
        startPagePanel.setBounds(0, 0, 500, 500);
        startPagePanel.setFocusable(true);
        startPagePanel.setBackground(new Color(120, 120, 120));
        startPagePanel.setOpaque(true);
        startPagePanel.setLayout(null);

        startPageLabel.setBounds(40, 0, 500, 40);
        register.setBounds(100, 230, 100, 40);
        login.setBounds(300, 230, 100, 40);
        register.addActionListener(e -> {
            startPagePanel.setVisible(false);
            registerPage.setVisible(true);
        });
        login.addActionListener(e -> {
            startPagePanel.setVisible(false);
            loginPage.setVisible(true);
        });

        startPagePanel.add(startPageLabel);
        startPagePanel.add(register);
        startPagePanel.add(login);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(startPagePanel);
        this.add(registerPage);
        this.add(loginPage);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
