package kbs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import org.bson.Document;

public class HomePage extends JFrame {
    JLabel someDescriptionOrSomethingIdontFuckingCare = new JLabel();
    static JTextArea notes = new JTextArea();
    JButton save = new JButton("Save");
    JLabel confirmationen = new JLabel("Notes have been saved");
    Timer balls = new Timer();

    HomePage(String name, Database db) {
        someDescriptionOrSomethingIdontFuckingCare.setText("Welcome, " + name);
        someDescriptionOrSomethingIdontFuckingCare.setBounds(200, 0, 400, 50);
        notes.setBounds(50, 50, 400, 300);
        notes.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
        notes.setFocusable(true);
        save.setFocusable(false);
        save.setBounds(200, 400, 100, 20);
        confirmationen.setForeground(new Color(15, 255, 0));
        confirmationen.setBounds(180, 410, 300, 50);
        confirmationen.setVisible(false);
        notes.addKeyListener(new Skull());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(someDescriptionOrSomethingIdontFuckingCare);
        this.add(notes);
        this.add(save);
        this.add(confirmationen);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        Document user = db.getUser(name);
        notes.setText(user.get("Notes").toString());

        save.addActionListener(e -> {
            db.saveNotes(notes.getText(), name);
            save.setEnabled(false);
            confirmationen.setVisible(true);
            balls.schedule(new TimerTask() {
                @Override
                public void run() {
                    save.setEnabled(true);
                    confirmationen.setVisible(false);
                }
            }, 1000);
        });
    }

    public class Skull extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            HomePage.notes.getText().replace(":skull", "my balls");
        }
    }
}
