package src;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private Database db = new Database();
    private ManagerPage managerPage = new ManagerPage(this);
    private BezorgerMenuPage bezorgerMenuPage = new BezorgerMenuPage(this);
    private RouteMenuPage routeMenuPage = new RouteMenuPage(this);
    private LoginPage loginPage = new LoginPage(this);
    //private BezorgerPage bezorgerPageOld = new BezorgerPage_old(this);
    private CardLayout cardLayout;
    private String userName;
    ApplicationFrame(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(loginPage, "loginPage");
        add(managerPage, "managerPage");
        add(bezorgerMenuPage, "bezorgerMenuPage");
        add(routeMenuPage, "routeMenuPage");

        cardLayout.show(this.getContentPane(), loginPage.getName());

        //setResizable(false);
        setVisible(true);
    }

    public void showManagerPage() {
        this.cardLayout.show(this.getContentPane(), "managerPage");
    }
    public void showBezorgerMenuPage() {
        this.cardLayout.show(this.getContentPane(), "bezorgerMenuPage");
    }
    public void showRouteMenuPage() {
        this.cardLayout.show(this.getContentPane(), "routeMenuPage");
    }

    public void showBezorgerPage() { this.cardLayout.show(this.getContentPane(), "bezorgerPage");}

    public static GridBagConstraints createGBC(int gridx, int gridy, int gridwith, int gridheight, int ipadx, int ipady, float weightx, float weighty, int fill){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwith;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.fill = fill;
        return constraints;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public Database getDb(){
        return db;
    }
}
