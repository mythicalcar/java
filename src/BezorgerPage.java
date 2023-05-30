package src;

import javax.swing.*;

public class BezorgerPage {
    private Database db;
    JPanel googleMapView = new JPanel();
    JPanel orderList = new JPanel();
    JScrollPane orderListScroller = new JScrollPane(orderList);
    private ApplicationFrame applicationFrame;
    BezorgerPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        db = applicationFrame.getDb();
    }
}
