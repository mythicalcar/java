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
        Distances distanceCalculator = new Distances();

        String sourcePlace = "Egypt banha";
        String destinationPlace = "Marroko tetouan";

        distanceCalculator.printDistance(sourcePlace,destinationPlace);
    }
}

