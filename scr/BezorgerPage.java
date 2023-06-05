package scr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BezorgerPage extends JPanel {
    //private Database db;
    JPanel googleMapView = new JPanel();
    JPanel orderList = new JPanel();
    JScrollPane orderListScroller = new JScrollPane(orderList);
    JButton openGoogleMaps = new JButton("Open route in google maps");
    JButton laadRoutes = new JButton("Refresh routes");
    JButton klaar = new JButton("Klaar");
    JButton fout = new JButton("Fout");

    String currentRoutePointer;

    MapsPage map = new MapsPage();
    ArrayList<Adress> route = new ArrayList<Adress>();
    Orders orders = new Orders();
    int i = 1;
    private JLabel menuTitleLabel = new JLabel();
    private ApplicationFrame applicationFrame;
    BezorgerPage(ApplicationFrame applicationFrame){
        this.applicationFrame = applicationFrame;
        //db = applicationFrame.getDb();
        // google view for bezorgers
        googleMapView.setBackground(new Color(100, 100, 100));
        googleMapView.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 5));
        googleMapView.add(openGoogleMaps);
        googleMapView.add(klaar);
        googleMapView.add(fout);
        googleMapView.add(laadRoutes);

        openGoogleMaps.setVisible(false);
        klaar.setVisible(false);
        fout.setVisible(false);

        // orderlist for navigations
        orderList.setBackground(new Color(100, 100, 100));
        orderList.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 5));
        orderList.setLayout(new BoxLayout(orderList, BoxLayout.Y_AXIS));

        orderListScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        orderListScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        orderListScroller.getVerticalScrollBar().setUnitIncrement(20);
        orderListScroller.getVerticalScrollBar().setBlockIncrement(100);

        openGoogleMaps.addActionListener(e -> {
            map.openGoogleMapsRoute(orders.nearestNeighbor());
        });

        laadRoutes.addActionListener(e -> {
            orderList.removeAll();
            route.clear();
            i = 1;
            for (Bestelling value : applicationFrame.getBestellingen()) {

                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(orderList.getWidth(), 50));
                panel.setBackground(Color.GRAY);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                int adressStartIndex = value.toString().indexOf("adress=") + 7;
                int adressEndIndex = value.toString().indexOf(",", adressStartIndex);
                String adress = value.toString().substring(adressStartIndex, adressEndIndex);
                String adressPointer = "Route" + i;
                JLabel label = new JLabel(adressPointer);
                panel.add(label);

                String[] adressPieces = adress.split(" ");
                Adress newAdress = new Adress(adressPieces[0], adressPieces[1], adressPieces[2], adressPieces[3]);
                route.add(newAdress);
                orders.setAdresses(route);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(label.getText());
                        currentRoutePointer = label.getText();
                        openGoogleMaps.setVisible(true);
                        klaar.setVisible(true);
                        fout.setVisible(true);
                    }});

                orderList.add(panel);
                i++;
            }
        });


        this.setBackground(new Color(180, 180, 180));
        this.setLayout(null);
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
    }
}