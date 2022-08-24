package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

    private DBConnect() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
