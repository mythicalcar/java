package scr;

import com.mysql.cj.xdevapi.AbstractDataResult;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Database db1 = new Database();
////        db1.selectNames(1);
//        Adress f1 = new Adress("Adam", "Abdelbakey", "rgr", "1056DN");
//
//    }

    public static void main(String[] args) {
        String apiKey = "YOUR_API_KEY";
        Distances distanceCalculator = new Distances();

        String sourcePlace = "Marco Polostraat 291A";
        String destinationPlace = "Almere";

        double distance = distanceCalculator.calculateDistance(sourcePlace, destinationPlace);

        if (distance >= 0) {
            System.out.println("Distance: " + distance + " km");
        } else {
            System.out.println("An error occurred while calculating the distance.");
        }
    }
}

