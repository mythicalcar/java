package scr;

import java.sql.*;

public class Database {

    String url = "jdbc:mysql://localhost:3306/nerd";
    String username = "root";
    String password = "";

    public void selectNames() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet city = statement.executeQuery("Select * from user;");

        while (city.next()){
            System.out.println(city.getInt(1) + " " + city.getString(2));
        }

        connection.close();
    }
}

