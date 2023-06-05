package scr;

import org.intellij.lang.annotations.Flow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BezorgerMenuPage extends JPanel implements ActionListener {
    ApplicationFrame applicationFrame;
    JPanel bezorgerScrollPanePanel  = new JPanel();
    JLabel menuTitleLabel = new JLabel("Bezorgers beheren");
//    JTable bezorgersJTable;
    JScrollPane bezorgersScrollPane;
    JPanel bezorgersScrollPanePanel = new JPanel();
    JPanel bezorgersScrollPanePanelPanel = new JPanel(new BorderLayout());
//    bezorgerScrollPanePanel
//    JPanel bezorgerScrollPaneHeader = new JPanel(new GridLayout(1, 4));
    //    Object[] bezorgersJTableColumns = {
//            //id?
//            "Naam",
//            "Email",
//            "Status"};
    ArrayList<Bezorger> bezorgers = new ArrayList<>();
    JButton backButton = new JButton("Terug");
    JButton addBezorgerButton = new JButton("Bezorger registreren");
    JButton refreshButton = new JButton("Bezorgers refreshen");
    JPanel flowPanel = new JPanel(new FlowLayout());

    public BezorgerMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new BorderLayout());

        bezorgersScrollPanePanel.setLayout(new GridLayout(0, 1));

        //make components, add listeners and constraints
        menuTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        menuTitleLabel.setPreferredSize(new Dimension(200, 100));

        bezorgerScrollPanePanel.setLayout(new BoxLayout(bezorgerScrollPanePanel, BoxLayout.Y_AXIS));

        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

//        bezorgers = applicationFrame.getDb().getBezorgers();
/*        DefaultTableModel model = new DefaultTableModel(bezorgers, bezorgersJTableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
        bezorgersJTable = new JTable(model);
        Dimension bezorgerTableSize = bezorgersJTable.getPreferredSize();
        bezorgersJTable.setPreferredScrollableViewportSize(bezorgerTableSize);
        bezorgersJTable.setCellSelectionEnabled(false);*/

//        JPanel bezorgerScrollPaneHeader
//        for (int i = 0; i < bezorgers.size(); i++) {
//            JPanel bezorgerPanel = createBezorgerPanel(bezorgers.get(i));
//            bezorgersScrollPanePanel.add(bezorgerPanel);
//        }

        refreshBezorgers();

        bezorgersScrollPanePanelPanel.add(bezorgersScrollPanePanel, BorderLayout.PAGE_START);
        bezorgersScrollPane = new JScrollPane(bezorgersScrollPanePanelPanel);//(bezorgersJTable);
        bezorgersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addBezorgerButton.addActionListener(this);
        addBezorgerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        backButton.addActionListener(this);

        flowPanel.add(addBezorgerButton);
        flowPanel.add(refreshButton);

        JPanel centerPanelPanel = new JPanel(new BorderLayout());
        //add components to centerpanel
        bezorgerScrollPanePanel.add(bezorgersScrollPane);

        centerPanelPanel.add(bezorgerScrollPanePanel, BorderLayout.CENTER);
        centerPanelPanel.add(flowPanel, BorderLayout.PAGE_END);

        //add components to pageendpanel
        pageEndPanel.add(backButton);

        //add components to bezorgermenupage
        this.add(menuTitleLabel, BorderLayout.PAGE_START);
        this.add(centerPanelPanel, BorderLayout.CENTER);
        this.add(pageEndPanel, BorderLayout.PAGE_END);
    }
    private JPanel createBezorgerPanel(Bezorger bezorger){
        JPanel bezorgerPanel = new JPanel(new BorderLayout());
        JPanel bezorgerPanelLeftPanel = new JPanel(new FlowLayout());
        JPanel bezorgerPanelRightPanel = new JPanel(new FlowLayout());
        JLabel bezorgerLabel = new JLabel("<html><span style='font-weight: normal;'>Gebruikersnaam:</span> " + bezorger.name + "</html>");
        JButton deleteButton = new JButton("Verwijderen");
//        JButton passwordButton = new JButton("Wachtwoord bekijken");
        //jlabel won't turn transparent...
        bezorgerLabel.setOpaque(false);
        bezorgerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        bezorgerPanelLeftPanel.add(bezorgerLabel);
//        bezorgerPanelRightPanel.add(passwordButton);
        bezorgerPanelRightPanel.add(deleteButton);
        bezorgerPanel.add(bezorgerPanelLeftPanel, BorderLayout.LINE_START);
        bezorgerPanel.add(bezorgerPanelRightPanel, BorderLayout.LINE_END);

        if(bezorger.status == 0) {
            deleteButton.setBackground(Color.red);
            deleteButton.addActionListener(e -> {
//in database nog een keer checken of de status niet veranderd is voor het verwijderen?
                //doe iets op basis van status
                int result = JOptionPane.showConfirmDialog(applicationFrame, "<html><span style='font-weight: normal;'>Wil je bezorger</span> " + bezorger.name + "</u> <span style='font-weight: normal;'>zeker</span> <span style='color: red;'>verwijderen</span>?</html>?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(result == 0){
                    applicationFrame.getDb().deleteBezorger(bezorger.id);
                    applicationFrame.refreshBezorgerAndRouteMenuPage();
                }
//                bezorgersScrollPane.remove(bezorgerPanel);
            });
        } else if (bezorger.status == 1) {
            deleteButton.setBackground(Color.gray);
            deleteButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(applicationFrame, "Deze bezorger is op dit moment aan het bezorgen.\nDruk op de refresh-knop om de status van de bezorgers opnieuw op te halen.");

                //bring up a dialogue: bezorger unavailable?
            });
        }
        return bezorgerPanel;
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
        if(e.getSource() == refreshButton){
            refreshBezorgers();
            applicationFrame.refreshRouteMenuPage();
//            applicationFrame.refreshBezorgerAndRouteMenuPage();
        }
    }
    public void refreshBezorgers(){
        bezorgers = applicationFrame.getDb().getBezorgers();
        bezorgersScrollPanePanel.removeAll();
        for (int i = 0; i < bezorgers.size(); i++) {
            JPanel bezorgerPanel = createBezorgerPanel(bezorgers.get(i));
            bezorgersScrollPanePanel.add(bezorgerPanel);
        }
        bezorgersScrollPanePanel.revalidate();
        bezorgersScrollPanePanel.repaint();
    }
}

