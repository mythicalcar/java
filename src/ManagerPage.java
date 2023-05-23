package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JPanel implements ActionListener {
    private JButton bezorgerMenuButton;
    private JButton routeMenuButton;
    private ApplicationFrame applicationFrame;
    public ManagerPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints;

        JLabel welcomeLabel = new JLabel("Welkom " + "manager!");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(0, 0, 150, 0);
        add(welcomeLabel, constraints);

        bezorgerMenuButton = new JButton("Bezorgers beheren");
        bezorgerMenuButton.addActionListener(this);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        add(bezorgerMenuButton, constraints);

        routeMenuButton = new JButton("Routes beheren");
        routeMenuButton.addActionListener(this);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(routeMenuButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bezorgerMenuButton){
            applicationFrame.showBezorgerMenuPage();
        }
        if(e.getSource() == routeMenuButton){
            applicationFrame.showRouteMenuPage();
        }
    }
}
