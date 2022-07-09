package database.persistence;

import database.connection.SQLConnection;
import rule.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriasDAO {

    // TODO - REMOVE THIS, ONLY FOR TESTS IN LOCALHOST
    public static void removeAll(){
        Connection connection = SQLConnection.getConnection();
        String sql = "DELETE FROM categorias";
        try {
            PreparedStatement result = connection.prepareStatement(sql);
            result.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean insert(Categorias c) {
        try {
            Connection connection = SQLConnection.getConnection();

            String sql = "INSERT INTO categorias (pessoa, carro, cidadeEstadoPais, fruta, objeto, animal, participante, " +
                    "letra_sorteada) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement result = connection.prepareStatement(sql);
            result.setString(1, c.getPessoa());
            result.setString(2, c.getCarro());
            result.setString(3, c.getCidadeEstadoPais());
            result.setString(4, c.getFruta());
            result.setString(5, c.getObjeto());
            result.setString(6, c.getAnimal());
            result.setInt(7, c.getParticipante().getId());
            result.setInt(8, c.getLetraSorteada());


            int success = result.executeUpdate();

            connection.close();

            return success == 1;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
