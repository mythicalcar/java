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

        gridBagPanel = new JPanel(new GridBagLayout());

        bezorgerJTable = new JTable();

        bezorgerScrollPane = new JScrollPane();

        backButton = new JButton("Terug");
        backButton.addActionListener(this);
        GridBagConstraints backButtonc = new GridBagConstraints();
        backButtonc.gridx = 0;
        backButtonc.gridy = 3;
        backButtonc.insets = new Insets(0, 50, 50, 0);
        backButtonc.anchor = GridBagConstraints.LAST_LINE_START;



        //add components to gridbagpanel
        gridBagPanel.add(backButton);

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
        ArrayList<String> bezorgers = new ArrayList<String>();
        return bezorgers;
    }
}
