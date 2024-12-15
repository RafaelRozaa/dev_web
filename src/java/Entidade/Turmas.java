package Entidade;

public class Turmas {

    private int id;
    private int professor_id;
    private int disciplina_id;
    private int aluno_id;
    private String nome_professor;
    private String nome_disciplina;
    private String nome_aluno;
    private String codigo_turma;
    private Float nota;

    public Turmas(int professor_id, int disciplina_id, int aluno_id, String codigo_turma, Float nota) {
        this.professor_id = professor_id;
        this.disciplina_id = disciplina_id;
        this.aluno_id = aluno_id;
        this.codigo_turma = codigo_turma;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome_Professor() {
        return nome_professor;
    }
    
    public void setNome_Professor(String nome_professor) {
        this.nome_professor = nome_professor;
    }
    
    public String getNome_Disciplina() {
        return nome_disciplina;
    }
    
    public void setNome_Disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }
        
    public String getNome_Aluno() {
        return nome_aluno;
    }
    
    public void setNome_Aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }
    
    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public int getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(int disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public String getCodigo_turma() {
        return codigo_turma;
    }

    public void setCodigo_turma(String codigo_turma) {
        this.codigo_turma = codigo_turma;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

}
