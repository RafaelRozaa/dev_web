package Entidade;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String celular;
    private String cpf;
    private String senha;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;

    public Aluno(String nome, String email, String celular, String cpf, String senha, String endereco, String cidade, String bairro, String cep) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
    }
    
    public Aluno(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }   
    public Aluno() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.endereco = "";
        this.senha = "";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

   


}
