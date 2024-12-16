package Model;

import java.sql.PreparedStatement;
import Entidade.Aluno;
import Entidade.Disciplina;
import Entidade.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Turmas;

public class TurmasDAO {

    public void Inserir(Turmas turma) throws Exception {
        Conexao conexao = new Conexao();
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)");
            sql.setInt(1, turma.getProfessor().getId());
            sql.setInt(2, turma.getDisciplina().getId());
            sql.setInt(3, turma.getAluno().getId());
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
            Turmas turma = new Turmas();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Turmas WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                turma.setId(resultado.getInt("ID"));
                Professor professor = new Professor(resultado.getInt("PROFESSOR_ID"));
                turma.setProfessor(professor);
                Aluno aluno = new Aluno(resultado.getInt("ALUNO_ID"));
                turma.setAluno(aluno);
                Disciplina disciplina = new Disciplina(resultado.getInt("DISCIPLINA_ID"));
                turma.setDisciplina(disciplina);
                turma.setCodigo_turma(resultado.getString("DISCIPLINA_ID"));
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
            sql.setInt(1, turma.getProfessor().getId());
            sql.setInt(2, turma.getDisciplina().getId());
            sql.setInt(3, turma.getAluno().getId());
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
                Turmas turma = new Turmas();
                Aluno aluno = new Aluno();
                Disciplina disciplina = new Disciplina();
                turma.setId(resultado.getInt("ID"));
                Professor professor = new Professor();
                turma.setCodigo_turma(resultado.getString("CODIGO_TURMA"));
                turma.setNota(resultado.getFloat("NOTA"));
                aluno.setId(resultado.getInt("ALUNO_ID"));
                disciplina.setId(resultado.getInt("DISCIPLINA_ID"));
                professor.setId(resultado.getInt("PROFESSOR_ID"));
                aluno.setNome(resultado.getString("NOME_ALUNO"));
                disciplina.setNome(resultado.getString("NOME_DISCIPLINA"));
                professor.setNome(resultado.getString("NOME_PROFESSOR"));
                turma.setProfessor(professor);
                turma.setDisciplina(disciplina);
                turma.setAluno(aluno);
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
