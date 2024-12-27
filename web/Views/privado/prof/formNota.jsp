<%@page import="Entidade.Turmas"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <title>Editar Nota</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</head>
        <%
            Turmas turma = (Turmas) request.getAttribute("turma");
        %>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />

    <div class="container mt-4">
        <h2 class="text-center mb-4">Editar Nota</h2>

        <form action="ProfessorController" method="post">
            <!-- Inputs ocultos com dados da turma -->
            <input type="hidden" name="turmaId" value="<%= turma.getId() %>">
            <input type="hidden" name="id" value="<%= turma.getProfessor().getId() %>">  <!-- ID do Professor -->
            <!-- Campo de nota -->
            <div class="mb-3">
                <label for="nota" class="form-label">Nota</label>
                <input type="number" step="1.0" class="form-control" id="nota" name="nota" value="<%= turma.getNota() %>" required>
            </div>

            <!-- Botões -->
            <button type="submit" class="btn btn-primary" name="btEnviar" value="editarNota">
                Salvar Alterações
            </button>
            <button type="button" onclick="history.back()" class="btn btn-secondary">Cancelar</button>
        </form>
    </div>
</body>
</html>
