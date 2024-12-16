package Entidade;

public class Turmas {

    private int id;
    private Aluno aluno;
    private Disciplina disciplina;
    private Professor professor;
    private String codigo_turma;
    private Float nota;

    public Turmas(int id) {
        this.id = id;
    }
    
    public Turmas() {
        this.id = 0;
        this.aluno = new Aluno();
        this.disciplina = new Disciplina();
        this.professor = new Professor();
        this.codigo_turma = "";
        this.nota = 0.0f;
    }
    
    public Turmas(int id, Aluno aluno, Disciplina disciplina, Professor professor, String codigo_turma, Float nota) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.professor = professor;
        this.codigo_turma = codigo_turma;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
