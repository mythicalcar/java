import java.sql.*;


public class DriverManager {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}
