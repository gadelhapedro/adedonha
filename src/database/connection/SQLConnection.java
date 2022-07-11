package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String URL = "jdbc:mysql://186.202.152.99/da_java?useSSL=false";
    private static final String USER = "da_java";
    private static final String PWD = "DB_test22!";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
