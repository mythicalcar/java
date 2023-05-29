package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RouteMenuPage extends JPanel implements ActionListener {

    private ApplicationFrame applicationFrame;
    private JPanel centerPanel;
    private JPanel lineStartPanel;
    private JLabel menuTitleLabel;
    private JScrollPane bezorgerScrollPane;
    private JButton backButton;
    public RouteMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, add listeners and constraints
        menuTitleLabel = new JLabel("Routes beheren");
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        lineStartPanel = new JPanel();
        lineStartPanel.setLayout(new BoxLayout(lineStartPanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        bezorgerScrollPane = new JScrollPane();
        //Get available bezorgers. For every available bezorger, put a button for them into the scrollpane.

        backButton = new JButton("Terug");
        backButton.addActionListener(this);

        //add components to linestartpanel
        lineStartPanel.add(bezorgerScrollPane);

        //add components to centerpanel

        //add components to pageendpanel
        pageEndPanel.add(backButton);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(lineStartPanel, BorderLayout.LINE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }

    private GridBagConstraints createGBC(int gridx, int gridy, int gridwith, int gridheight){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwith;
        constraints.gridheight = gridheight;
        return constraints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            applicationFrame.showManagerPage();
        }
    }
}
