package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BezorgerMenuPage extends JPanel implements ActionListener {
    private JButton backButton;
    private ApplicationFrame applicationFrame;
    private JPanel gridBagPanel;
    private JLabel menuTitleLabel;
    private JTable bezorgerJTable;
    private JScrollPane bezorgerScrollPane;
    private String[] columnNames = {
            "Naam",
            "idk",
    };
    public BezorgerMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, add listeners and constraints
        menuTitleLabel = new JLabel("Bezorgers beheren");
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        gridBagPanel = new JPanel(new GridBagLayout());

        String[] bezorgerJTableColumns = {
                //id?
                "Naam",
                "Locatie",
                "Status"};
        Object[][] bezorgers = getBezorgers();
        bezorgerJTable = new JTable(bezorgers, bezorgerJTableColumns);

        TextArea bezorgerTextArea = new TextArea();
        bezorgerScrollPane = new JScrollPane(bezorgerJTable);
        GridBagConstraints bezorgerScrollPanec = createGBC(1, 0,3, 3);
        bezorgerScrollPanec.anchor = GridBagConstraints.CENTER;
        bezorgerScrollPanec.fill = GridBagConstraints.HORIZONTAL;

        Component strut1 = Box.createHorizontalStrut(1);
        GridBagConstraints strut1c = createGBC(0, 0, 1, 1);

        Component strut2 = Box.createHorizontalStrut(1);
        GridBagConstraints strut2c = createGBC(4, 0, 1, 1);

        Component strut3 = Box.createHorizontalStrut(1);
        GridBagConstraints strut3c = createGBC(0, 1, 1, 1);

        Component strut4 = Box.createHorizontalStrut(1);
        GridBagConstraints strut4c = createGBC(4, 1, 1, 1);


        backButton = new JButton("Terug");
        backButton.addActionListener(this);
        GridBagConstraints backButtonc = createGBC(0, 3, 1, 1);
        //backButtonc.insets = new Insets(0, 50, 50, 0);
        backButtonc.anchor = GridBagConstraints.LAST_LINE_START;

        //add components to gridbagpanel
        gridBagPanel.add(bezorgerScrollPane, bezorgerScrollPanec);
        gridBagPanel.add(strut1, strut1c);
        gridBagPanel.add(strut2, strut2c);
        gridBagPanel.add(strut3, strut3c);
        gridBagPanel.add(strut4, strut4c);
        gridBagPanel.add(backButton, backButtonc);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(gridBagPanel, BorderLayout.CENTER);
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
            setVisible(false);
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
