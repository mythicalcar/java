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
        Object[][] dummyData = {
                {"Bezorger 1", "Almere", Integer.valueOf(0)},
                {"Bezorger 2", "Almere", Integer.valueOf(0)},
                {"Bezorger 3", "Almere", Integer.valueOf(0)}
        };
        bezorgerJTable = new JTable(dummyData, bezorgerJTableColumns);

        TextArea bezorgerTextArea = new TextArea();
        bezorgerScrollPane = new JScrollPane(bezorgerJTable);
        GridBagConstraints bezorgerScrollPanec = new GridBagConstraints();
        bezorgerScrollPanec.gridx = 1;
        bezorgerScrollPanec.gridy = 2;
        bezorgerScrollPanec.gridwidth = 3;
        bezorgerScrollPanec.gridheight = 2;
        bezorgerScrollPanec.anchor = GridBagConstraints.CENTER;

        backButton = new JButton("Terug");
        backButton.addActionListener(this);
        GridBagConstraints backButtonc = new GridBagConstraints();
        backButtonc.gridx = 0;
        backButtonc.gridy = 3;
        backButtonc.gridwidth = 1;
        //backButtonc.insets = new Insets(0, 50, 50, 0);
        backButtonc.anchor = GridBagConstraints.LAST_LINE_START;



        //add components to gridbagpanel
        gridBagPanel.add(bezorgerScrollPane, bezorgerScrollPanec);
        gridBagPanel.add(backButton, backButtonc);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(gridBagPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            setVisible(false);
        }
    }

    private ArrayList<String> getBezorgers(){
        //update this to get all bezorgers from the mongodb bezorgers collection
        ArrayList<String> bezorgers = new ArrayList<String>();
        return bezorgers;
    }
}
