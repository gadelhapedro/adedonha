package database.persistence;

import database.connection.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LetrasDAO {

    public static ArrayList<String> getLetras() {
        Connection connection = SQLConnection.getConnection();
        String sql = "SELECT letra FROM letras";
        ArrayList<String> letras = new ArrayList<String>();
        try {
            PreparedStatement result = connection.prepareStatement(sql);
            ResultSet rs = result.executeQuery();
            while (rs.next()) {
                letras.add(rs.getString("letra"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return letras;
    }

}
