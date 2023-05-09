package scr;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    String url = "jdbc:mysql://localhost:3306/nerdygadgets";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jbdc.Driver");

            Connection connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("e");
        }
    }
}
