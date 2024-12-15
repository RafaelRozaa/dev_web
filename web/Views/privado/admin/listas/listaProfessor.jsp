<%@page import="java.util.List"%>
<%@page import="Entidade.Professor"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Professores</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    
    <div class="container mt-5">
        <h1>Lista de Professores</h1>
        
        <div class="mb-3">
            <a href="ProfessorController?action=incluir" class="btn btn-success">Incluir Professor</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>CPF</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Professor> listaProfessores = (List<Professor>) request.getAttribute("listaProfessores");
                    if (listaProfessores != null && !listaProfessores.isEmpty()) {
                        for (Professor professor : listaProfessores) {
                %>
                <tr>
                    <td><%= professor.getNome() %></td>
                    <td><%= professor.getEmail() %></td>
                    <td><%= professor.getCpf() %></td>
                    <td>
                        <!-- Botão de editar -->
                        <a href="ProfessorController?action=alterar&id=<%= professor.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <!-- Botão de excluir -->
                        <a href="ProfessorController?action=excluir&id=<%= professor.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="4" class="text-center">Nenhum professor encontrado.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
