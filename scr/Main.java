package scr;

//import com.mysql.cj.xdevapi.AbstractDataResult;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Orders aa = new Orders();
//        MapsPage.openGoogleMapsRoute(aa.nearestNeighbor());
        //aa.addAdress(new Adress("Amsterdam", "Soerabajastraat", "4", "1095GP" ));
        //aa.addAdress(new Adress("Almere", "Audioweg", "2", "1322AV" ));
        //aa.addAdress(new Adress("Almere", "Televisieweg", "2", "1322AC"));
//        System.out.println(aa.sortAddressesByPostcode());

        //System.out.println(aa.nearestNeighbor());
       // LoginPage loginPage = new LoginPage();

        Database db = new Database();
        ArrayList<Adress> eee =  db.selectAdress();
        System.out.println(eee);
    }
}

