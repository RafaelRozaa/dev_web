<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidade.Turmas"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário de Turma</title>
        <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/Views/comum/menu.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <%
                        Turmas turma = (Turmas) request.getAttribute("turma");
                        String action = (String) request.getAttribute("action");
                        if (turma == null) {
                            turma = new Turmas();
                        }
                    %>
                    <h1 class="mb-2">
                        <% if ("incluir".equals(action)) { %>
                        Criar Turma
                        <% } else if ("alterar".equals(action)) { %>
                        Alterar Turma
                        <% } else if ("excluir".equals(action)) { %>
                        Excluir Turma
                        <% }%>
                    </h1>

                    <!-- Formulário de turma -->
                    <form action="TurmasController?action=<%= action %>" method="POST">
                        <!-- Campo oculto para enviar o ID -->
                        <input type="hidden" name="id" value="<%= turma.getId()%>">

                        <!-- Código da Turma -->
                        <div class="mb-3">
                            <label for="codigo_turma" class="form-label">Código da Turma</label>
                            <input type="text" name="codigo_turma" id="codigo_turma" required
                                   class="form-control"
                                   <%= "excluir".equals(action) ? "readonly" : ""%>
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? turma.getCodigo_turma() : ""%>
                                   >
                        </div>

                        <!-- Professor -->
                        <div class="mb-3">
                            <label for="professor_id" class="form-label">Professor</label>
                            <input type="text" name="professor_id" id="professor_id" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? turma.getProfessor().getId() : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Disciplina -->
                        <div class="mb-3">
                            <label for="disciplina_id" class="form-label">Disciplina</label>
                            <input type="text" name="disciplina_id" id="disciplina_id" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? turma.getDisciplina().getId() : ""%>
                                   
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Aluno -->
                        <div class="mb-3">
                            <label for="aluno_id" class="form-label">Aluno</label>
                            <input type="text" name="aluno_id" id="aluno_id" required
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? turma.getAluno().getId() : ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Nota -->
                        <div class="mb-3">
                            <label for="nota" class="form-label">Nota</label>
                            <input type="number" name="nota" id="nota" required min="0" max="10" step="0.1"
                                   class="form-control"
                                   value=<%= "excluir".equals(action) || "alterar".equals(action) ? turma.getNota() : ""%>
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
                            <a href="TurmasController?action=listar" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                    <!-- Exibir mensagem de erro, se existir -->
                    <%
                        String msgError = (String) request.getAttribute("msgError");
                        if (msgError != null && !msgError.isEmpty()) {
                    %>
                    <div class="alert alert-danger mt-3">
                        <%= msgError%>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
