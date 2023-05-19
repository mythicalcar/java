package scr;

import java.sql.*;

public class Database {

    static String url = "jdbc:mysql://localhost:3306/nerdy";
    static String username = "root";
    static String password = "";

    public static void selectNames(int index) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet city = statement.executeQuery("Select Name, Surname\n" +
                "From Users\n" +
                "Where ID =" + index + ";");

        while (city.next()){
            System.out.println(city.getString(1) + " " + city.getString(2));}

        connection.close();
    }
}

