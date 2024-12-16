<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidade.Aluno"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário Aluno</title>
        <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/Views/comum/menu.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <%
                        Aluno aluno = (Aluno) request.getAttribute("aluno");
                        String action = (String) request.getAttribute("action");
                        if (aluno == null) {
                            aluno = new Aluno();
                        }
                    %>
                    <h1 class="mb-2">
                        <% if ("incluir".equals(action)) { %>
                        Criar Aluno
                        <% } else if ("alterar".equals(action)) { %>
                        Alterar Aluno
                        <% } else if ("excluir".equals(action)) { %>
                        Excluir Aluno
                        <% } %>
                    </h1>

                    <!-- Exibir mensagem de erro, se existir -->
                    <%
                        String msgError = (String) request.getAttribute("msgError");
                        if (msgError != null && !msgError.isEmpty()) {
                    %>
                    <div class="alert alert-danger">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <!-- Formulário de aluno -->
                    <form action="AlunoController" method="POST">
                        <!-- Campo oculto para enviar o ID -->
                        <input type="hidden" name="id" value="<%= aluno.getId()%>">

                        <!-- Nome -->
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" id="nome" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getNome()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email"required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getEmail()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- CPF -->
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" id="cpf" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getCpf()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Senha -->
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" id="senha" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getSenha()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Endereço -->
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" id="endereco" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getEndereco()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Cidade -->
                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" id="cidade" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getCidade()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Celular -->
                        <div class="mb-3">
                            <label for="celular" class="form-label">Celular</label>
                            <input type="text" name="celular" id="celular" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getCelular()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Bairro -->
                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" id="bairro" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getBairro()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- CEP -->
                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" id="cep" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? aluno.getCep()  : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Botões -->
                        <div class="d-flex justify-content-between">
                            <button type="submit" name="btEnviar" value="<%="incluir".equals(action) ? "criar"
                                    : "alterar".equals(action) ? "alterar"
                                    : "excluir".equals(action) ? "excluir" : ""%>" class="btn <%= "excluir".equals(action) ? "btn-danger" : "btn-primary"%>">
                                <%="incluir".equals(action) ? "Criar"
                                        : "alterar".equals(action) ? "Alterar"
                                        : "excluir".equals(action) ? "Excluir" : ""%>
                            </button>
                            <a href="AlunoController?action=listar" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                    <%-- Exibe mensagem de erro, caso exista --%>
                    <%
                        if ((msgError != null) && (!msgError.isEmpty())) {
                    %>
                    <div class="alert alert-danger mt-3" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
