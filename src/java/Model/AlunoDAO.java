package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Aluno;

public class AlunoDAO {

    public void Inserir(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "INSERT INTO Alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?,?,?,?,?,?,?,?,?)"
            );
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Aluno", e);
        } finally {
            conexao.closeConexao();
        }
    }

    public Aluno getAluno(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Aluno aluno = new Aluno();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setCelular(resultado.getString("celular"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setSenha(resultado.getString("senha"));
                aluno.setEndereco(resultado.getString("endereco"));
                aluno.setCidade(resultado.getString("cidade"));
                aluno.setBairro(resultado.getString("bairro"));
                aluno.setCep(resultado.getString("cep"));
            }
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Aluno", e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "UPDATE Alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ? WHERE id = ?"
            );
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            sql.setInt(10, aluno.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Aluno", e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Alunos WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir Aluno", e);
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Aluno> ListaDeAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Alunos ORDER BY nome";
            PreparedStatement sql = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = sql.executeQuery();
            while (resultado.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setCelular(resultado.getString("celular"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setSenha(resultado.getString("senha"));
                aluno.setEndereco(resultado.getString("endereco"));
                aluno.setCidade(resultado.getString("cidade"));
                aluno.setBairro(resultado.getString("bairro"));
                aluno.setCep(resultado.getString("cep"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Alunos", e);
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }

    public Aluno Logar(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "SELECT * FROM Alunos WHERE cpf = ? AND senha = ? LIMIT 1"
            );
            sql.setString(1, aluno.getCpf());
            sql.setString(2, aluno.getSenha());
            ResultSet resultado = sql.executeQuery();
            Aluno alunoObtido = new Aluno();
            if (resultado.next()) {
                alunoObtido = new Aluno();
                alunoObtido.setId(resultado.getInt("id"));
                alunoObtido.setNome(resultado.getString("nome"));
                alunoObtido.setEmail(resultado.getString("email"));
                alunoObtido.setCelular(resultado.getString("celular"));
                alunoObtido.setCpf(resultado.getString("cpf"));
                alunoObtido.setSenha(resultado.getString("senha"));
                alunoObtido.setEndereco(resultado.getString("endereco"));
                alunoObtido.setCidade(resultado.getString("cidade"));
                alunoObtido.setBairro(resultado.getString("bairro"));
                alunoObtido.setCep(resultado.getString("cep"));
            }
            return alunoObtido;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao logar Aluno", e);
        } finally {
            conexao.closeConexao();
        }
    }
}