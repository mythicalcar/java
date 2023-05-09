package scr;

import java.sql.*;

public class Database {

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/nerdygadgets";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jbdc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet city = statement.executeQuery("select CityID, CityName from cities;");

            while (city.next()){
                System.out.println(city.getInt(1) + " " + city.getString(2));
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("e");
        } catch (SQLException e) {
            System.out.println("suii");;
        }
    }
}
