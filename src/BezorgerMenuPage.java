package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BezorgerMenuPage extends JPanel implements ActionListener {

    private ApplicationFrame applicationFrame;
    private JPanel centerPanel;
    private JLabel menuTitleLabel;
    private JTable bezorgersJTable;
    private JScrollPane bezorgersScrollPane;
    private String[] bezorgersJTableColumns = {
            //id?
            "Naam",
            "Locatie",
            "Status"};
    private Object[][] bezorgers;
    private JButton backButton;
    private JButton addBezorgerButton;
    public BezorgerMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, add listeners and constraints
        menuTitleLabel = new JLabel("Bezorgers beheren");
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        bezorgers = getBezorgers();
        bezorgersJTable = new JTable(bezorgers, bezorgersJTableColumns);
        Dimension bezorgerTableSize = bezorgersJTable.getPreferredSize();
        bezorgersJTable.setPreferredScrollableViewportSize(bezorgerTableSize);
        bezorgersJTable.setCellSelectionEnabled(false);

        bezorgersScrollPane = new JScrollPane(bezorgersJTable);
        bezorgersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addBezorgerButton = new JButton("Bezorger toevoegen");
        addBezorgerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        backButton = new JButton("Terug");
        backButton.addActionListener(this);

        //add components to centerpanel
        centerPanel.add(bezorgersScrollPane);
        centerPanel.add(addBezorgerButton);

        //add components to pageendpanel
        pageEndPanel.add(backButton);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
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

    private Object[][] getBezorgers(){
        //update this to get all bezorgers from the mongodb bezorgers collection
        Object[][] dummyData = {
                {"Bezorger 1", "Almere", Integer.valueOf(0)},
                {"Bezorger 2", "Almere", Integer.valueOf(0)},
                {"Bezorger 3", "Almere", Integer.valueOf(0)}
        };
        return dummyData;
    }
}
