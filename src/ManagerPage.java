package src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPage extends JPanel implements ActionListener {
    private JButton bezorgerMenuButton;
    private JButton routeMenuButton;
    private ApplicationFrame applicationFrame;
    private JPanel gridBagPanel;
    private JLabel welcomeLabel;
    private JLabel emptyBottomLabel;
    public ManagerPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, bind actions and define constraints if needed
        gridBagPanel = new JPanel(new GridBagLayout());
        gridBagPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        welcomeLabel = new JLabel("Welkom " + "manager!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setPreferredSize(new Dimension(200, 100));

        emptyBottomLabel = new JLabel();
        emptyBottomLabel.setPreferredSize(new Dimension(200, 100));

        bezorgerMenuButton = new JButton("Bezorgers beheren");
        bezorgerMenuButton.addActionListener(this);
        GridBagConstraints bezorgerMenuButtonc = new GridBagConstraints();
        bezorgerMenuButtonc.gridx = 1;
        bezorgerMenuButtonc.gridy = 1;
        bezorgerMenuButtonc.gridwidth = 2;
        bezorgerMenuButtonc.gridheight = 1;
        bezorgerMenuButtonc.fill = GridBagConstraints.HORIZONTAL;
        routeMenuButton = new JButton("Routes beheren");
        routeMenuButton.addActionListener(this);
        GridBagConstraints routeMenuButtonc = new GridBagConstraints();
        routeMenuButtonc.gridx = 1;
        routeMenuButtonc.gridy = 2;
        routeMenuButtonc.gridwidth = 2;
        routeMenuButtonc.gridheight = 1;
        routeMenuButtonc.fill = GridBagConstraints.HORIZONTAL;

        //add components to gridbagpanel
        gridBagPanel.add(bezorgerMenuButton, bezorgerMenuButtonc);
        gridBagPanel.add(routeMenuButton, routeMenuButtonc);
        //add components to applicationframe
        this.add(welcomeLabel, BorderLayout.PAGE_START);
        this.add(gridBagPanel, BorderLayout.CENTER);
        this.add(emptyBottomLabel, BorderLayout.PAGE_END);


//        constraints = new GridBagConstraints();
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.gridwidth = 4;
//        constraints.gridheight = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
//        constraints.anchor = GridBagConstraints.PAGE_START;
//        constraints.insets = new Insets(0, 0, 150, 0);
//        add(welcomeLabel, constraints);




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
