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
    private JScrollPane bezorgersScrollPane;
    private JButton backButton;
    private JList bezorgerList;
    private ArrayList<Bezorger> bezorgers = new ArrayList<Bezorger>();
    private ArrayList<JButton> bezorgersButtons = new ArrayList<JButton>();
    private JButton[] selectedBezorgerButtons;
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

        updateBezorgers();
        for (Bezorger bezorger:bezorgers) {
            JButton bezorgerButton = new JButton(bezorger.name);
            bezorgersButtons.add(bezorgerButton);
        }
        bezorgersScrollPane = new JScrollPane(bezorgerList);
        //Get available bezorgers. For every available bezorger, put a button for them into the scrollpane.

        backButton = new JButton("Terug");
        backButton.addActionListener(this);

        //add buttons to bezorgersscrollpane
        for(int i = 0; i < bezorgersButtons.size(); i++){
            bezorgersScrollPane.add(bezorgersButtons.get(i));
        }

        //add components to linestartpanel
        lineStartPanel.add(bezorgersScrollPane, BorderLayout.PAGE_START);

        //add components to centerpanel

        //add components to pageendpanel
        pageEndPanel.add(backButton);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(lineStartPanel, BorderLayout.LINE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }

    private void updateBezorgers(){
        //bezorgers ophalen vanuit database die een status van beschikbaar hebben
        bezorgers.add(new Bezorger("Bezorger 1"));
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
        for (JButton bezorgerButton:bezorgersButtons) {
            //if (bezorgerButton.getBackground())
            bezorgerButton.setBackground(Color.gray);
        }
    }
}
