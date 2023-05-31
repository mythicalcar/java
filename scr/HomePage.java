package scr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

import org.bson.Document;

public class HomePage extends JFrame {
    Timer timer = new Timer();
    JLabel name;
    Document user;

    HomePage(String name, Database db) {
        user = db.getUser(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(500, 500));
        this.setFocusable(true);
        this.setBounds(0, 0, 500, 500);
        this.setBackground(new Color(120, 120, 120));
        this.setLocationRelativeTo(null);
        this.name = new JLabel(name);
        this.name.setBounds(250, 250, 100, 100);
        this.add(this.name);
        this.pack();
        this.setVisible(true);
    }

    public class KeyInputAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
        }
    }
}
