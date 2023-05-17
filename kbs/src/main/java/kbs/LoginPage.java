package kbs;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    LoginPage(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JButton loginButton = new JButton();
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(loginButton);


        setVisible(true);
    }
}
