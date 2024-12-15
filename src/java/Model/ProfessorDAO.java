package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Professor;

public class ProfessorDAO {

    public void Inserir(Professor professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Professores (nome, email, cpf, senha) VALUES (?, ?, ?, ?)");
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir professor: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public Professor getProfessor(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Professor professor = new Professor(null, null, null, null);
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                professor.setId(resultado.getInt("ID"));
                professor.setNome(resultado.getString("NOME"));
                professor.setEmail(resultado.getString("EMAIL"));
                professor.setCpf(resultado.getString("CPF"));
                professor.setSenha(resultado.getString("SENHA"));
            }
            return professor;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professor: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Professor professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Professores SET nome = ?, email = ?, cpf = ?, senha = ? WHERE ID = ?");
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.setInt(5, professor.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar professor: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Professores WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir professor: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Professor> ListaDeProfessores() {
        ArrayList<Professor> professores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Professores ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Professor professor = new Professor(null, null, null, null);
                professor.setId(resultado.getInt("ID"));
                professor.setNome(resultado.getString("NOME"));
                professor.setEmail(resultado.getString("EMAIL"));
                professor.setCpf(resultado.getString("CPF"));
                professor.setSenha(resultado.getString("SENHA"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar professores: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return professores;
    }

    public Professor Logar(Professor professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE cpf = ? AND senha = ? LIMIT 1");
            sql.setString(1, professor.getCpf());
            sql.setString(2, professor.getSenha());
            ResultSet resultado = sql.executeQuery();
            Professor professorObtido = new Professor(null, null, null, null);
            if (resultado.next()) {
                professorObtido.setId(resultado.getInt("ID"));
                professorObtido.setNome(resultado.getString("NOME"));
                professorObtido.setEmail(resultado.getString("EMAIL"));
                professorObtido.setCpf(resultado.getString("CPF"));
                professorObtido.setSenha(resultado.getString("SENHA"));
            }
            return professorObtido;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }
}
