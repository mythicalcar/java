package scr;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BezorgerMenuPage extends JPanel implements ActionListener {
    ApplicationFrame applicationFrame;
    JPanel centerPanel  = new JPanel();
    JLabel menuTitleLabel = new JLabel("Bezorgers beheren");;
    JTable bezorgersJTable;
    JScrollPane bezorgersScrollPane;
    Object[] bezorgersJTableColumns = {
            //id?
            "Naam",
            "Email",
            "Status"};
    Object[][] bezorgers;
    JButton backButton = new JButton("Terug");
    JButton addBezorgerButton = new JButton("Bezorger toevoegen");

    public BezorgerMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        //make components, add listeners and constraints
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        bezorgers = applicationFrame.getDb().getBezorgerDataTable();
        DefaultTableModel model = new DefaultTableModel(bezorgers, bezorgersJTableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
        bezorgersJTable = new JTable(model);
        Dimension bezorgerTableSize = bezorgersJTable.getPreferredSize();
        bezorgersJTable.setPreferredScrollableViewportSize(bezorgerTableSize);
        bezorgersJTable.setCellSelectionEnabled(false);

        bezorgersScrollPane = new JScrollPane(bezorgersJTable);
        bezorgersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addBezorgerButton.addActionListener(this);
        addBezorgerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

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
        if(e.getSource() == addBezorgerButton){
            applicationFrame.showRegisterPage();
        }
    }
}

