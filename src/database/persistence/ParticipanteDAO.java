package database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rule.Participante;
import database.connection.SQLConnection;

public class ParticipanteDAO {

	public static Participante listarPorId(int id) {

		Participante participante = new Participante();

		try {
			Connection conexao = SQLConnection.getConnection();

			PreparedStatement sql = conexao.prepareStatement("select * from participante where id=?");

			sql.setInt(1, id);

			ResultSet result = sql.executeQuery();

			while (result.next()) {
				participante.setNome(result.getString("nome"));
				participante.setPontuacao(result.getInt("pontuacao"));
			}

			conexao.close();

		} catch (Exception e) {
		}
		return participante;
	}

	public ArrayList<Participante> listar() {

		ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();

		try {
			Connection conexao = SQLConnection.getConnection();

			ResultSet result = conexao
					.prepareStatement("SELECT * FROM participante order by dataCadastroParticipante DESC")
					.executeQuery();

			Participante participante;

			while (result.next()) {
				participante = new Participante();

				participante.setNome(result.getString("nome"));
				participante.setPontuacao(result.getInt("pontuacao"));

				listaParticipantes.add(participante);
			}
			conexao.close();
		} catch (SQLException e) {
		} finally {
		}
		return listaParticipantes;
	}

	public static boolean inserir(Participante a) {
		int valor = 0;
		try {
			Connection conexao = SQLConnection.getConnection();

			PreparedStatement result = conexao
					.prepareStatement("INSERT INTO participante (nome, pontuacao) VALUES (?, ?);");

			result.setString(1, a.getNome());
			result.setInt(2, a.getPontuacao());

			valor = result.executeUpdate();
			conexao.close();
		} catch (Exception e) {

		}

		if (valor == 1)
			return true;
		else
			return false;

	}

	public void deletar(int id) {
		try {

			Connection conexao = SQLConnection.getConnection();

			PreparedStatement result = conexao.prepareStatement("DELETE FROM participante WHERE id = ?;");

			result.setInt(1, id);

			result.executeUpdate();

			conexao.close();
		} catch (Exception e) {

		}
	}

	public boolean update(Participante a) {
		int valor = 0;
		try {
			Connection conexao = SQLConnection.getConnection();

			PreparedStatement result = conexao
					.prepareStatement("UPDATE participante SET nome = ?, pontuacao =? WHERE id = ?");

			result.setString(1, a.getNome());
			result.setInt(2, a.getPontuacao());
			result.setInt(3, a.getId());

			valor = result.executeUpdate();
			conexao.close();
		} catch (Exception e) {

		}

		if (valor == 1)
			return true;
		else
			return false;

	}

}
