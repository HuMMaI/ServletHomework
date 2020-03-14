package servlet.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/user_db";
    private static String USERNAME = "root";
    private static String PASSWORD = "Qwerty156qqqq";

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t connection to db!");
        }
    }
}
