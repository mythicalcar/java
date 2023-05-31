package scr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;

public class RouteMenuPage extends JPanel implements ActionListener {
    private ApplicationFrame applicationFrame;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JLabel menuTitleLabel;
    private JScrollPane bezorgersScrollPane;
    private JButton backButton;
    //private JList bezorgerList;
    private ArrayList<Bezorger> bezorgers = new ArrayList<Bezorger>();
//    private ArrayList<JButton> bezorgersButtons = new ArrayList<JButton>();
    //private JButton[] selectedBezorgerButtons;
    //private JButton generateRouteButton = new JButton("Routes genereren");;
    //private JButton assignRouteButton = new JButton("Routes toewijzen");;
    public RouteMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, add listeners and constraints
        menuTitleLabel = new JLabel("Routes beheren");
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        centerPanel = new JPanel(new GridBagLayout());

        JPanel mapPanel = new JPanel();
        mapPanel.setBackground(Color.red);

//        JPanel routePanel = new JPanel();
//        routePanel.setBackground(Color.blue);

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel bezorgersScrollPanePanel = new JPanel();
        bezorgersScrollPanePanel.setLayout(new GridLayout(0, 1));
        JPanel bezorgersScrollPanePanelPanel = new JPanel(new BorderLayout());
        //Get available bezorgers. For every available bezorger, put a button for them into the scrollpane.
        updateBezorgers();
        int bezorgerButtonHeight = 10;
        int bezorgerButtonWidth = 0;
        for (Bezorger bezorger:bezorgers) {
            JButton bezorgerButton = new JButton(bezorger.name);
/*
            bezorgersButtons.add(bezorgerButton);
            JButton sillyButton = new JButton("filler");
            JButton sillyButton2 = new JButton("filler");
            JButton sillyButton3 = new JButton("filler");
            JButton sillyButton4 = new JButton("filler");
            bezorgersScrollPanePanel.add(sillyButton);
            bezorgersScrollPanePanel.add(sillyButton2);
            bezorgersScrollPanePanel.add(sillyButton3);
            bezorgersScrollPanePanel.add(sillyButton4);*/
            bezorgersScrollPanePanel.add(bezorgerButton);
            if(bezorger.status == 0){
                bezorgerButton.setBackground(Color.green);
                bezorgerButton.addActionListener(e -> {
                    JOptionPane.showConfirmDialog(this, "Route toewijzen aan " + bezorger.name + "?", "", JOptionPane.OK_CANCEL_OPTION);
                });
            } else if (bezorger.status == 1) {
                bezorgerButton.setBackground(Color.orange);
            }
//            bezorgerButtonHeight = bezorgerButton.getPreferredSize().height;
//            bezorgerButtonWidth =  bezorgerButton.getPreferredSize().width;

        }
        bezorgersScrollPanePanelPanel.add(bezorgersScrollPanePanel, BorderLayout.PAGE_START);
        bezorgersScrollPane = new JScrollPane(bezorgersScrollPanePanelPanel);
        //bezorgersScrollPane.setPreferredSize(new Dimension(bezorgersScrollPanePanel.getPreferredSize().width, bezorgersScrollPane.getPreferredSize().height));
        bezorgersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        backButton = new JButton("Terug");
        backButton.addActionListener(this);

        //add buttons to bezorgersscrollpane
/*        for(int i = 0; i < bezorgersButtons.size(); i++){
            bezorgersScrollPane.add(bezorgersButtons.get(i));
        }*/

        //add components to leftpanel
        //have a jlabel giving the manager instructions in the bottom left of the page?
        leftPanel.add(bezorgersScrollPane);
//        leftPanel.add(bezorgersScrollPane, ApplicationFrame.createGBC(0, 0, 1, 5,0,0, 1, 1, GridBagConstraints.BOTH));
        //leftPanel.add(generateRouteButton, ApplicationFrame.createGBC(0, 2, 1, 1, 0,0,0, 0, GridBagConstraints.HORIZONTAL));
        //leftPanel.add(assignRouteButton, ApplicationFrame.createGBC(0, 4, 1, 1, 0, 0, 0, 1, GridBagConstraints.HORIZONTAL));

        //add components to centerpanel
        centerPanel.add(leftPanel, ApplicationFrame.createGBC(0, 0, 1, 5, 0,0,0.25f, 0, GridBagConstraints.BOTH));
        centerPanel.add(mapPanel, ApplicationFrame.createGBC(1, 0, 4, 5, 0,0,1, 1, GridBagConstraints.BOTH));
//        centerPanel.add(routePanel, ApplicationFrame.createGBC(1, 3, 4, 2, 0,0,1, 0.5f, GridBagConstraints.BOTH));

        //add components to pageendpanel
        pageEndPanel.add(backButton);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        //this.add(lineStartPanel, BorderLayout.LINE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }

    private void updateBezorgers(){
        //bezorgers ophalen vanuit database die een status van beschikbaar hebben
        bezorgers = applicationFrame.getDb().getBezorgers();
        //System.out.println(bezorgersArray);
//        bezorgers.add(new Bezorger("Bezorger 1"));
//        bezorgers.add(new Bezorger("Bezorger 1"));
//        bezorgers.add(new Bezorger("Bezorger 1"));
//        bezorgers.add(new Bezorger("Bezorger 1"));
//        bezorgers.add(new Bezorger("Bezorger 1"));
//        bezorgers.add(new Bezorger("Bezorger 1"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            applicationFrame.showManagerPage();
        }
    }
}
