package scr;

import com.mysql.cj.xdevapi.AbstractDataResult;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Database db1 = new Database();
////        db1.selectNames(1);
//        Adress f1 = new Adress("Adam", "Abdelbakey", "rgr", "1056DN");
//
//    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Distances distanceCalculator = new Distances();

        Adress sourcePlace = new Adress("Amsterdam", "Marco Polostraat" , "200");
        Adress destinationPlace = new Adress("Almere", "Schuttersstraat", "14");
//        System.out.println(sourcePlace);
        distanceCalculator.printDistance(sourcePlace,destinationPlace);

//        Database.selectNames(7);
        Orders aa = new Orders();
        aa.addAdress(new Adress("Amsterdam", "Prinsengracht", "14"));
        aa.addAdress(new Adress("Amsterdam", "Pampuslaan", "4"));
        aa.addAdress(new Adress("Beesd", "Stationsweg", "3"));
        aa.addAdress(new Adress("Middelburg", "Oosterkerkplein", "1"));
        ArrayList<Adress> route = aa.nearestNeighbor();
        MapsPage.openGoogleMapsRoute(route);
    }
}

