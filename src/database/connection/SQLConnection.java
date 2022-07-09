package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/trabalho_locahost?useSSL=false";
    private static final String USER = "root";
    private static final String PWD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
