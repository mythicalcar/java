package scr;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BezorgerPage extends JFrame {
    JPanel googleMapView = new JPanel();
    JPanel orderList = new JPanel();
    JScrollPane orderListScroller = new JScrollPane(orderList);

    BezorgerPage(String name, Database db) {
        // google view for bezorgers
        googleMapView.setBackground(new Color(100, 100, 100));
        googleMapView.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 5));

        // orderlist for navigations
        orderList.setBackground(new Color(100, 100, 100));
        orderList.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 5));
        orderList.setLayout(new BoxLayout(orderList, BoxLayout.Y_AXIS));

        orderListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        orderListScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        orderListScroller.getVerticalScrollBar().setUnitIncrement(20);
        orderListScroller.getVerticalScrollBar().setBlockIncrement(100);

        for (int i = 1; i <= 200; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(orderList.getWidth(), 50));
            panel.setBackground(Color.GRAY);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            JLabel label = new JLabel("label" + i);
            panel.add(label);

            orderList.add(panel);
        }

        this.setTitle("Home");
        this.getContentPane().setBackground(new Color(180, 180, 180));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1000, 800));

        // Create a wrapper panel for googleMapView
        JPanel googleMapViewWrapper = new JPanel(new BorderLayout());
        googleMapViewWrapper.setBackground(new Color(180, 180, 180));
        googleMapViewWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
        googleMapViewWrapper.setBounds(200, 150, 500, 500); // Set bounds for googleMapViewWrapper
        googleMapViewWrapper.add(googleMapView, BorderLayout.CENTER);

        // Calculate the position and width for the orderListWrapper
        int orderListWidth = 300;
        int orderListX = 700;
        int orderListY = 50;
        int orderListHeight = 700;
        JPanel orderListWrapper = new JPanel(new BorderLayout());
        orderListWrapper.setBackground(new Color(180, 180, 180));
        orderListWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        orderListWrapper.setBounds(orderListX, orderListY, orderListWidth, orderListHeight);
        orderListWrapper.add(orderListScroller, BorderLayout.CENTER);

        this.add(googleMapViewWrapper);
        this.add(orderListWrapper);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
