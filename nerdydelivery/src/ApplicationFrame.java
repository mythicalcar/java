import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame(){
        setTitle("NerdyDelivery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        GridLayout layout = new GridLayout(0, 3);
        setLayout(layout);

        JPanel courierList = new JPanel();
        JLabel courierListLabel = new JLabel("Bezorgers");
        courierList.add(courierListLabel);
        add(courierList);

        setVisible(true);
    }
}
