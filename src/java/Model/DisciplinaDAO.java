package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Disciplina;

public class DisciplinaDAO {

    // Método para inserir uma nova disciplina no banco de dados
    public void Inserir(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Disciplina (nome, requisito, ementa, carga_horaria)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCarga_horaria());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a disciplina: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    // Método para obter uma disciplina pelo seu ID
    public Disciplina getDisciplina(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Disciplina disciplina = new Disciplina("", "", "", 0);
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Disciplina WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null && resultado.next()) {
                disciplina.setId(resultado.getInt("ID"));
                disciplina.setNome(resultado.getString("NOME"));
                disciplina.setRequisito(resultado.getString("REQUISITO"));
                disciplina.setEmenta(resultado.getString("EMENTA"));
                disciplina.setCarga_horaria(resultado.getInt("CARGA_HORARIA"));
            }
            return disciplina;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a disciplina: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    // Método para atualizar os dados de uma disciplina
    public void Alterar(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE ID = ?");
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCarga_horaria());
            sql.setInt(5, disciplina.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a disciplina: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    // Método para excluir uma disciplina pelo ID
    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Disciplina WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a disciplina: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    // Método para listar todas as disciplinas
    public ArrayList<Disciplina> ListaDeDisciplinas() {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Disciplina ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado != null && resultado.next()) {
                Disciplina disciplina = new Disciplina(
                        resultado.getString("NOME"),
                        resultado.getString("REQUISITO"),
                        resultado.getString("EMENTA"),
                        resultado.getInt("CARGA_HORARIA")
                );
                disciplina.setId(resultado.getInt("ID"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as disciplinas: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }
}
