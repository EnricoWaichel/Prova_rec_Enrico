package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.carros.Montadora;

import model.repository.Banco;
import model.repository.BaseRepository;

public class MontadoraRepository implements BaseRepository <Montadora>{

    @Override
    public Montadora salvar(Montadora novaEntidade) {
        Connection conn = Banco.getConnection();
        String query = "INSERT INTO Montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, novaEntidade.getNome());
            stmt.setString(2, novaEntidade.getPaisFundacao());
            stmt.setString(3, novaEntidade.getNomePresidente());
            stmt.setDate(4, novaEntidade.getDataFundacao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        novaEntidade.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao salvar montadora");
            System.out.println("Erro: " + erro.getMessage());
            return null;
        } finally {
            Banco.closeConnection(conn);
        }
        return novaEntidade;
    }
    @Override
    public Montadora consultarPorId(int id) {
        Connection conn = Banco.getConnection();
        String query = "SELECT * FROM Montadora WHERE id = ?";
        Montadora montadora = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    montadora = new Montadora();
                    montadora.setId(resultado.getInt("id"));
                    montadora.setNome(resultado.getString("nome"));
                    montadora.setPaisFundacao(resultado.getString("paisFundacao"));
                    montadora.setNomePresidente(resultado.getString("nomePresidente"));
                    montadora.setDataFundacao(resultado.getDate("dataFundacao"));
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar montadora com o id: " + id);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }
        return montadora;
    }

	@Override
	public boolean excluir(int id) {
		return false;
	}

	@Override
	public boolean alterar(Montadora entidade) {
		return false;
	}

	@Override
	public ArrayList<Montadora> consultarTodos() {
		return null;
	}
}