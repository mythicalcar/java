package kbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BezorgerMenuPage extends JPanel implements ActionListener {
    private JButton backButton;
    private ApplicationFrame applicationFrame;
    public BezorgerMenuPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        backButton = new JButton("Terug");
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
