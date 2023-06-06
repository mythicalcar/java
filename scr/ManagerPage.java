package scr;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JPanel implements ActionListener {
    JButton bezorgerMenuButton = new JButton("Bezorgers beheren");
    JButton routeMenuButton = new JButton("Routes beheren");
    JButton logOutButton = new JButton("Log uit");
    ApplicationFrame applicationFrame;
    JPanel gridBagPanel = new JPanel(new GridBagLayout());;
    JLabel welcomeLabel = new JLabel("Welkom " + "null" +"!");;
    JPanel pageEndPanel = new JPanel();

    public ManagerPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, bind actions and define constraints if needed
        gridBagPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        //gridBagPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setPreferredSize(new Dimension(200, 100));

        pageEndPanel.setPreferredSize(new Dimension(200, 100));

        bezorgerMenuButton.addActionListener(this);
        GridBagConstraints bezorgerMenuButtonc = new GridBagConstraints();
        bezorgerMenuButtonc.gridx = 0;
        bezorgerMenuButtonc.gridy = 0;
        bezorgerMenuButtonc.gridwidth = 2;
        bezorgerMenuButtonc.gridheight = 1;
        bezorgerMenuButtonc.fill = GridBagConstraints.HORIZONTAL;

        routeMenuButton.addActionListener(this);
        GridBagConstraints routeMenuButtonc = new GridBagConstraints();
        routeMenuButtonc.gridx = 0;
        routeMenuButtonc.gridy = 1;
        routeMenuButtonc.gridwidth = 2;
        routeMenuButtonc.gridheight = 1;
        routeMenuButtonc.fill = GridBagConstraints.HORIZONTAL;

        logOutButton.addActionListener(this);


        //add components to gridbagpanel
        gridBagPanel.add(bezorgerMenuButton, bezorgerMenuButtonc);
        gridBagPanel.add(routeMenuButton, routeMenuButtonc);
        gridBagPanel.add(logOutButton, ApplicationFrame.createGBCWithInsets(00, 2, 2, 1, 0, 0, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0)));
        //add components to applicationframe
        this.add(welcomeLabel, BorderLayout.PAGE_START);
        this.add(gridBagPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bezorgerMenuButton){
            applicationFrame.showBezorgerMenuPage();
        }
        if(e.getSource() == routeMenuButton){
            applicationFrame.showRouteMenuPage();
        }
        if(e.getSource() == logOutButton){
            applicationFrame.showLoginPage();
        }
    }
    public void updateName(String name){
        welcomeLabel.setText("Welkom " + name +"!");
    }
}