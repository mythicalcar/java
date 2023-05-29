package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RouteMenuPage extends JPanel implements ActionListener {

    private ApplicationFrame applicationFrame;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JLabel menuTitleLabel;
    private JScrollPane bezorgersScrollPane;
    private JButton backButton;
    private JList bezorgerList;
    private ArrayList<Bezorger> bezorgers = new ArrayList<Bezorger>();
    private ArrayList<JButton> bezorgersButtons = new ArrayList<JButton>();
    private JButton[] selectedBezorgerButtons;
    private JButton generateRouteButton;
    private JButton assignRouteButton;
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

        JPanel routePanel = new JPanel();
        routePanel.setBackground(Color.blue);

        leftPanel = new JPanel(new GridBagLayout());
        //leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel bezorgersScrollPanePanel = new JPanel();
        bezorgersScrollPanePanel.setLayout(new GridLayout(0, 1));
        //Get available bezorgers. For every available bezorger, put a button for them into the scrollpane.
        updateBezorgers();
        int bezorgerButtonHeight = 0;
        for (Bezorger bezorger:bezorgers) {
            JButton bezorgerButton = new JButton(bezorger.name);
            //bezorgersButtons.add(bezorgerButton);
            bezorgersScrollPanePanel.add(bezorgerButton);
            bezorgerButtonHeight = bezorgerButton.getSize().height;
        }
        System.out.println(bezorgerButtonHeight);
        bezorgersScrollPane = new JScrollPane(bezorgersScrollPanePanel);
        bezorgersScrollPane.setPreferredSize(new Dimension(1000, bezorgerButtonHeight * 5));
        bezorgersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        backButton = new JButton("Terug");
        backButton.addActionListener(this);

        //add buttons to bezorgersscrollpane
/*        for(int i = 0; i < bezorgersButtons.size(); i++){
            bezorgersScrollPane.add(bezorgersButtons.get(i));
        }*/

        generateRouteButton = new JButton("Routes genereren");

        assignRouteButton = new JButton("Routes toewijzen");

        //add components to leftpanel
        leftPanel.add(bezorgersScrollPane, createGBC(0, 0, 1, 2,0,0, 1, 0.5f, GridBagConstraints.BOTH));
        leftPanel.add(generateRouteButton, createGBC(0, 2, 1, 1, 0,0,0, 0, GridBagConstraints.HORIZONTAL));
        leftPanel.add(assignRouteButton, createGBC(0, 4, 1, 1, 0, 0, 0, 1, GridBagConstraints.HORIZONTAL));

        //add components to centerpanel
        centerPanel.add(leftPanel, createGBC(0, 0, 1, 5, 0,0,0, 0, GridBagConstraints.BOTH));
        centerPanel.add(mapPanel, createGBC(1, 0, 4, 3, 0,0,1, 1, GridBagConstraints.BOTH));
        centerPanel.add(routePanel, createGBC(1, 3, 4, 2, 0,0,1, 0.5f, GridBagConstraints.BOTH));

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
        bezorgers.add(new Bezorger("Bezorger 1"));
        bezorgers.add(new Bezorger("Bezorger 1"));
        bezorgers.add(new Bezorger("Bezorger 1"));
        bezorgers.add(new Bezorger("Bezorger 1"));
        bezorgers.add(new Bezorger("Bezorger 1"));
        bezorgers.add(new Bezorger("Bezorger 1"));
    }

    private GridBagConstraints createGBC(int gridx, int gridy, int gridwith, int gridheight, int ipadx, int ipady, float weightx, float weighty, int fill){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwith;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.fill = fill;
        return constraints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            applicationFrame.showManagerPage();
        }
        for (JButton bezorgerButton:bezorgersButtons) {
            //if (bezorgerButton.getBackground())
            bezorgerButton.setBackground(Color.gray);
        }
    }
}
