package scr;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ManagerPage extends JFrame {
    JButton send = new JButton("Verstuur bezorging naar ww");

    ManagerPage(String name, Database db) {
        this.setTitle("Home");
        this.getContentPane().setBackground(new Color(180, 180, 180));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1000, 800));
        this.add(send);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
