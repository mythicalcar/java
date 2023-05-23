package kbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JPanel implements ActionListener {
    private JButton bezorgerMenuButton;
    private JButton routeMenuButton;
    public ManagerPage(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        bezorgerMenuButton = new JButton("Bezorgers beheren");
        bezorgerMenuButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(bezorgerMenuButton);

        routeMenuButton = new JButton("Routes beheren");
        routeMenuButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(routeMenuButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bezorgerMenuButton){

        }
        if(e.getSource() == routeMenuButton){

        }
    }
}
