package scr;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ApplicationFrame extends JFrame {
    private Database db = new Database();
    private ManagerPage managerPage = new ManagerPage(this);
    private BezorgerMenuPage bezorgerMenuPage = new BezorgerMenuPage(this);
    private RouteMenuPage routeMenuPage = new RouteMenuPage(this);
    private LoginPage loginPage = new LoginPage(this);
    private BezorgerPage bezorgerPage = new BezorgerPage(this);
    private RegisterPage registerPage = new RegisterPage(this);
    private CardLayout cardLayout;
    private String userName;
    private String userId;
    private String bezorgerId;
    private ArrayList<Bestelling> bestellingen;
    ApplicationFrame(){
        setSize(800, 600);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(loginPage, "loginPage");
        add(managerPage, "managerPage");
        add(bezorgerMenuPage, "bezorgerMenuPage");
        add(routeMenuPage, "routeMenuPage");
        add(bezorgerPage, "bezorgerPage");
        add(registerPage, "registerPage");

        cardLayout.show(this.getContentPane(), loginPage.getName());

        //setResizable(false);
        setVisible(true);
    }

    public void showManagerPage() {
        this.cardLayout.show(this.getContentPane(), "managerPage");
        managerPage.updateName(userName);
    }
    public void showBezorgerMenuPage() {
        this.cardLayout.show(this.getContentPane(), "bezorgerMenuPage");
    }
    public void showRouteMenuPage() {
        this.cardLayout.show(this.getContentPane(), "routeMenuPage");
    }
    public void showBezorgerPage() {
        this.cardLayout.show(this.getContentPane(), "bezorgerPage");
        bezorgerPage.updateName(userName);
    }
    public void showRegisterPage(){
        this.cardLayout.show(this.getContentPane(), "registerPage");
    }
    public void showLoginPage(){
        this.cardLayout.show(this.getContentPane(), "loginPage");
    }
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
    public static GridBagConstraints createGBCWithInsets(int gridx, int gridy, int gridwith, int gridheight, int ipadx, int ipady, float weightx, float weighty, int fill, Insets insets){
        GridBagConstraints constraints = createGBC(gridx, gridy, gridwith, gridheight, ipadx, ipady, weightx, weighty, fill);
        constraints.insets = insets;
        return constraints;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setBezorgerId(String id) {
        this.bezorgerId = id;
    }
    public String getBezorgerId(){
        return this.bezorgerId;
    }

    public void setBestellingen(ArrayList<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    public ArrayList<Bestelling> getBestellingen(){
        return this.bestellingen;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public Database getDb(){
        return db;
    }
    public void refreshBezorgerAndRouteMenuPage(){
        //i don't know why, but this isn't refreshing the bezorger list on either of the pages
        bezorgerMenuPage.refreshBezorgers();
        routeMenuPage.refreshBezorgers();
    }
    public void refreshBezorgerMenuPage(){
//        if(bezorgerMenuPage != null){
            bezorgerMenuPage.refreshBezorgers();
//        }
    }
    public void refreshRouteMenuPage(){
//        if(routeMenuPage != null){
            routeMenuPage.refreshBezorgers();
//        }
    }
}
