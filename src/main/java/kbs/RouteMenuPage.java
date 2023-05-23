package kbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RouteMenuPage extends JPanel implements ActionListener {
    private JButton backButton;
    private ApplicationFrame applicationFrame;
    private ArrayList<String> bezorgers = new ArrayList<String>();
    public RouteMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        backButton = new JButton("Terug");
        backButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(backButton);

        JList bezorgerList = new JList(bezorgersro);
        backButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            setVisible(false);
        }
    }
}
