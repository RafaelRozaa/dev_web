package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Turmas;

public class TurmasDAO {

    public void Inserir(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)");
            sql.setInt(1, turma.getProfessor_id());
            sql.setInt(2, turma.getDisciplina_id());
            sql.setInt(3, turma.getAluno_id());
            sql.setString(4, turma.getCodigo_turma());
            sql.setFloat(5, turma.getNota());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir turma: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public Turmas getTurma(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Turmas turma = new Turmas(0, 0, 0, null, null);
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Turmas WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                turma.setId(resultado.getInt("ID"));
                turma.setProfessor_id(resultado.getInt("PROFESSOR_ID"));
                turma.setDisciplina_id(resultado.getInt("DISCIPLINA_ID"));
                turma.setAluno_id(resultado.getInt("ALUNO_ID"));
                turma.setCodigo_turma(resultado.getString("CODIGO_TURMA"));
                turma.setNota(resultado.getFloat("NOTA"));
            }
            return turma;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ? WHERE ID = ?");
            sql.setInt(1, turma.getProfessor_id());
            sql.setInt(2, turma.getDisciplina_id());
            sql.setInt(3, turma.getAluno_id());
            sql.setString(4, turma.getCodigo_turma());
            sql.setFloat(5, turma.getNota());
            sql.setInt(6, turma.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Turmas WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turmas> ListaDeTurmas() {
        ArrayList<Turmas> turmas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT turmas.id, turmas.professor_id, turmas.disciplina_id, turmas.aluno_id, turmas.codigo_turma, turmas.nota, alunos.nome as nome_aluno, disciplina.nome as nome_disciplina, professores.nome as nome_professor FROM Turmas JOIN alunos ON turmas.aluno_id = alunos.id JOIN professores ON turmas.professor_id = professores.id JOIN disciplina ON turmas.disciplina_id = disciplina.id ORDER BY codigo_turma";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Turmas turma = new Turmas(0, 0, 0, null, null);
                turma.setId(resultado.getInt("ID"));
                turma.setProfessor_id(resultado.getInt("PROFESSOR_ID"));
                turma.setDisciplina_id(resultado.getInt("DISCIPLINA_ID"));
                turma.setAluno_id(resultado.getInt("ALUNO_ID"));
                turma.setCodigo_turma(resultado.getString("CODIGO_TURMA"));
                turma.setNota(resultado.getFloat("NOTA"));
                turma.setNome_Aluno(resultado.getString("NOME_ALUNO"));
                turma.setNome_Disciplina(resultado.getString("NOME_DISCIPLINA"));
                turma.setNome_Professor(resultado.getString("NOME_PROFESSOR"));
                turmas.add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }
}
