<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidade.Disciplina"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário Disciplina</title>
        <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/Views/comum/menu.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <% 
                        Disciplina disciplina = (Disciplina) request.getAttribute("disciplina");
                        String action = (String) request.getAttribute("action");
                        if (disciplina == null) {
                            disciplina = new Disciplina("", "", "", 0);
                        }
                    %>
                    <h1 class="mb-2">
                        <% if ("incluir".equals(action)) { %>
                            Criar Disciplina
                        <% } else if ("alterar".equals(action)) { %>
                            Alterar Disciplina
                        <% } else if ("excluir".equals(action)) { %>
                            Excluir Disciplina
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

                    <!-- Formulário de disciplina -->
                    <form action="DisciplinaController" method="POST">
                        <!-- Campo oculto para enviar o ID -->
                        <input type="hidden" name="id" value="<%= disciplina.getId() %>">

                        <!-- Nome -->
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" id="nome" required
                                class="form-control"
                                value=<%= "excluir".equals(action) || "alterar".equals(action) ? disciplina.getNome()  : ""%>
                                <%= "excluir".equals(action) ? "readonly" : "" %> >
                        </div>

                        <!-- Requisito -->
                        <div class="mb-3">
                            <label for="requisito" class="form-label">Requisito</label>
                            <input type="text" name="requisito" id="requisito" required
                                class="form-control"
                                value=<%= "excluir".equals(action) || "alterar".equals(action) ? disciplina.getRequisito()  : ""%>
                                <%= "excluir".equals(action) ? "readonly" : "" %> >
                        </div>

                        <!-- Ementa -->
                        <div class="mb-3">
                            <label for="ementa" class="form-label">Ementa</label>
                            <input type="text" name="ementa" id="ementa" required
                                class="form-control"
                                value=<%= "excluir".equals(action) || "alterar".equals(action) ? disciplina.getEmenta()  : ""%>
                                <%= "excluir".equals(action) ? "readonly" : "" %> >
                        </div>

                        <!-- Carga Horária -->
                        <div class="mb-3">
                            <label for="carga_horaria" class="form-label">Carga Horária</label>
                            <input type="number" name="carga_horaria" id="carga_horaria" required
                                class="form-control"
                                value=<%= "excluir".equals(action) || "alterar".equals(action) ? disciplina.getCarga_horaria()  : ""%>
                                <%= "excluir".equals(action) ? "readonly" : "" %> >
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
                            <a href="DisciplinaController?action=listar" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
