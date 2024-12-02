
package Entidade;


public class Disciplina {
    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private int carga_horaria;

    public Disciplina(String nome, String requisito, String ementa, int carga_horaria) {
        this.nome = nome;
        this.requisito = requisito;
        this.ementa = ementa;
        this.carga_horaria = carga_horaria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRequisito() {
        return requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    
    
}
