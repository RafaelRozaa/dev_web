<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidade.Professor"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário Professor</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <% 
                    Professor professor = (Professor) request.getAttribute("professor");
                    String action = (String) request.getAttribute("action");
                    if (professor == null) {
                        professor = new Professor("", "", "", "");
                    }
                %>
                <h1 class="mb-2">
                    <% if ("incluir".equals(action)) { %>
                        Criar Professor
                    <% } else if ("alterar".equals(action)) { %>
                        Alterar Professor
                    <% } else if ("excluir".equals(action)) { %>
                        Excluir Professor
                    <% } %>
                </h1>

                <!-- Exibir mensagem de erro, se existir -->
                <% 
                    String msgError = (String) request.getAttribute("msgError");
                    if (msgError != null && !msgError.isEmpty()) { 
                %>
                <div class="alert alert-danger">
                    <%= msgError %>
                </div>
                <% } %>

                <!-- Formulário de professor -->
                <form action="ProfessorController" method="POST">
                    <!-- Campo oculto para enviar o ID -->
                    <input type="hidden" name="id"value="<%= professor.getId() %>">

                    <!-- Nome -->
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" name="nome" id="nome" required
                            class="form-control" 
                             value=<%= "excluir".equals(action) || "alterar".equals(action) ? professor.getNome() : ""%>
                            <%= "excluir".equals(action) ? "readonly" : "" %>>
                    </div>

                    <!-- Email -->
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" id="email" required
                            class="form-control"
                            value=<%= "excluir".equals(action) || "alterar".equals(action) ? professor.getEmail() : ""%>
                            <%= "excluir".equals(action) ? "readonly" : "" %>>
                    </div>

                    <!-- CPF -->
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" id="cpf" required
                            class="form-control"
                            value=<%= "excluir".equals(action) || "alterar".equals(action) ? professor.getCpf()  : ""%>
                            <%= "excluir".equals(action) ? "readonly" : "" %>>
                    </div>

                    <!-- Senha -->
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" name="senha" id="senha" required
                            class="form-control"
                            <%= "excluir".equals(action) ? "readonly" : "" %>>
                    </div>

                    <!-- Botões -->
                    <div class="d-flex justify-content-between">
                        <button type="submit" name="btEnviar" value="<%= 
                            "incluir".equals(action) ? "criar" : 
                            "alterar".equals(action) ? "alterar" : 
                            "excluir".equals(action) ? "excluir" : ""
                        %>" class="btn <%= "excluir".equals(action) ? "btn-danger" : "btn-primary" %>">
                            <%= 
                                "incluir".equals(action) ? "Criar" : 
                                "alterar".equals(action) ? "Alterar" : 
                                "excluir".equals(action) ? "Excluir" : ""
                            %>
                        </button>
                        <a href="ProfessorController?action=listar" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
