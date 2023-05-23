package kbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {
    private JButton loginButton;
    private ApplicationFrame applicationFrame;
    public LoginPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){

        }
    }
}
