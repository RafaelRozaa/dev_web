package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Administrador;

public class AdministradorDAO {

    public void Inserir(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Administrador (nome, cpf, senha, aprovado, endereco)"
                    + " VALUES (?,?,?,?,?)");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getSenha());
            sql.setString(4, Administrador.getAprovado());
            sql.setString(5, Administrador.getEndereco());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Administrador getAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Administrador Administrador = new Administrador();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    Administrador.setNome(resultado.getString("NOME"));
                    Administrador.setCpf(resultado.getString("CPF"));
                    Administrador.setEndereco(resultado.getString("ENDERECO"));
                    Administrador.setSenha(resultado.getString("SENHA"));
                    Administrador.setAprovado(resultado.getString("APROVADO"));
                }
            }
            return Administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Administrador SET nome = ?, cpf = ?, endereco = ?, senha = ?, aprovado = ?  WHERE ID = ? ");
            sql.setString(1, Administrador.getNome());
            sql.setString(2, Administrador.getCpf());
            sql.setString(3, Administrador.getEndereco());
            sql.setString(4, Administrador.getSenha());
            sql.setString(5, Administrador.getAprovado());
            sql.setInt(6, Administrador.getId());
            System.out.println(Administrador.getAprovado());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o administrador: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Administrador WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Administrador> ListaDeAdministrador() {
        ArrayList<Administrador> meusAdministradores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Administrador order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador Administrador = new Administrador();
                    Administrador.setNome(resultado.getString("NOME"));
                    Administrador.setCpf(resultado.getString("CPF"));
                    Administrador.setSenha(resultado.getString("SENHA"));
                    Administrador.setEndereco(resultado.getString("ENDERECO"));
                    Administrador.setAprovado(resultado.getString("aprovado"));
                    Administrador.setId(Integer.parseInt(resultado.getString("id")));
                    
                    meusAdministradores.add(Administrador);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAdministradores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAdministradores;
    }

    public Administrador Logar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, Administrador.getCpf());
            sql.setString(2, Administrador.getSenha());
            ResultSet resultado = sql.executeQuery();
            Administrador AdministradorObtido = new Administrador();
            if (resultado.next()) {
                    AdministradorObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    AdministradorObtido.setNome(resultado.getString("NOME"));
                    AdministradorObtido.setCpf(resultado.getString("CPF"));
                    AdministradorObtido.setEndereco(resultado.getString("ENDERECO"));
                    AdministradorObtido.setAprovado(resultado.getString("APROVADO"));
                    AdministradorObtido.setSenha(resultado.getString("SENHA"));
            }
            return AdministradorObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
