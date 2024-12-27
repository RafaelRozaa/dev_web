package Model;

import java.sql.PreparedStatement;
import Entidade.Aluno;
import Entidade.Disciplina;
import Entidade.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidade.Turmas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public ArrayList<Turmas> TurmasPorCod() {
       ArrayList<Turmas> turmas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT turmas.codigo_turma, disciplina.nome as nome_disciplina, professores.nome as nome_professor FROM Turmas JOIN  professores ON turmas.professor_id = professores.id JOIN disciplina ON turmas.disciplina_id = disciplina.id GROUP BY codigo_turma, nome_disciplina, nome_professor";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Turmas turma = new Turmas();
                Disciplina disciplina = new Disciplina();
                Professor professor = new Professor();
                turma.setCodigo_turma(resultado.getString("CODIGO_TURMA"));
                disciplina.setNome(resultado.getString("NOME_DISCIPLINA"));
                professor.setNome(resultado.getString("NOME_PROFESSOR"));
                turma.setProfessor(professor);
                turma.setDisciplina(disciplina);
                turmas.add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }
    
    public ArrayList<Turmas> TurmaRelatorio(String codigoTurma) {
        ArrayList<Turmas> turmas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = """
                SELECT 
                    turmas.id, 
                    turmas.professor_id, 
                    turmas.disciplina_id, 
                    turmas.aluno_id, 
                    turmas.codigo_turma, 
                    turmas.nota, 
                    alunos.nome as nome_aluno, 
                    disciplina.nome as nome_disciplina, 
                    professores.nome as nome_professor
                FROM 
                    Turmas
                JOIN 
                    alunos ON turmas.aluno_id = alunos.id
                JOIN 
                    professores ON turmas.professor_id = professores.id
                JOIN 
                    disciplina ON turmas.disciplina_id = disciplina.id
                WHERE 
                    turmas.codigo_turma = ?
                ORDER BY 
                    turmas.codigo_turma
            """;
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            preparedStatement.setString(1, codigoTurma); // Definir o parâmetro de filtro
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                Turmas turma = new Turmas();
                Aluno aluno = new Aluno();
                Disciplina disciplina = new Disciplina();
                Professor professor = new Professor();

                // Preencher os dados da turma e entidades relacionadas
                turma.setId(resultado.getInt("ID"));
                turma.setCodigo_turma(resultado.getString("CODIGO_TURMA"));
                turma.setNota(resultado.getFloat("NOTA"));

                aluno.setId(resultado.getInt("ALUNO_ID"));
                aluno.setNome(resultado.getString("NOME_ALUNO"));

                disciplina.setId(resultado.getInt("DISCIPLINA_ID"));
                disciplina.setNome(resultado.getString("NOME_DISCIPLINA"));

                professor.setId(resultado.getInt("PROFESSOR_ID"));
                professor.setNome(resultado.getString("NOME_PROFESSOR"));

                // Configurar os relacionamentos
                turma.setAluno(aluno);
                turma.setDisciplina(disciplina);
                turma.setProfessor(professor);

                // Adicionar à lista
                turmas.add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas por código: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }
    
    public Map<String, List<Turmas>> turmasAgrupadasPorCodigo(int professorId) {
        Map<String, List<Turmas>> turmasAgrupadas = new HashMap<>();
        Conexao conexao = new Conexao();

        try {
            // SQL para buscar turmas, incluindo o ID de aluno e disciplina
            String selectSQL = """
                SELECT
                    turmas.id,
                    turmas.codigo_turma,
                    turmas.aluno_id,
                    turmas.disciplina_id,
                    alunos.nome AS nome_aluno,
                    disciplina.nome AS nome_disciplina,
                    turmas.nota
                FROM
                    turmas
                INNER JOIN
                    disciplina ON turmas.disciplina_id = disciplina.id
                INNER JOIN
                    alunos ON turmas.aluno_id = alunos.id
                WHERE
                    turmas.professor_id = ?
                ORDER BY
                    turmas.codigo_turma
            """;

            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            preparedStatement.setInt(1, professorId);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                Turmas turma = new Turmas();
                Aluno aluno = new Aluno();
                Disciplina disciplina = new Disciplina();

                turma.setId(resultado.getInt("id"));
                turma.setCodigo_turma(resultado.getString("codigo_turma"));
                turma.setNota(resultado.getFloat("nota"));
                

                // Coletando os IDs
                int alunoId = resultado.getInt("aluno_id");
                int disciplinaId = resultado.getInt("disciplina_id");

                aluno.setId(alunoId);
                aluno.setNome(resultado.getString("nome_aluno"));

                disciplina.setId(disciplinaId);
                disciplina.setNome(resultado.getString("nome_disciplina"));              

                turma.setAluno(aluno);
                turma.setDisciplina(disciplina);

                // Agrupar pelo código da turma
                turmasAgrupadas
                    .computeIfAbsent(turma.getCodigo_turma(), k -> new ArrayList<>())
                    .add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turmas agrupadas: " + e.getMessage(), e);
        } finally {
            conexao.closeConexao();
        }
        return turmasAgrupadas;
    }
     
    public void editarNota(int idTurma, float nota) {
        String sql = "UPDATE turmas SET nota = ? WHERE id = ?";
        Conexao conexao = new Conexao();
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setFloat(1, nota);
            stmt.setInt(2, idTurma);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar nota: " + e.getMessage(), e);
        } finally {
        conexao.closeConexao(); 
        }
    }

    
}
