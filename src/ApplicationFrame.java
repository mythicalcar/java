package src;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private ManagerPage managerPage = new ManagerPage(this);
    private BezorgerMenuPage bezorgerMenuPage = new BezorgerMenuPage(this);
    private RouteMenuPage routeMenuPage = new RouteMenuPage(this);
    private CardLayout cardLayout;

    ApplicationFrame(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(managerPage, "managerPage");
        add(bezorgerMenuPage, "bezorgerMenuPage");
        add(routeMenuPage, "routeMenuPage");

        cardLayout.show(this.getContentPane(), managerPage.getName());

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
}
