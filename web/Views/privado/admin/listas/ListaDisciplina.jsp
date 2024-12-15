<%@page import="java.util.List"%>
<%@page import="Entidade.Disciplina"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Disciplinas</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    
    <div class="container mt-5">
        <h1>Lista de Disciplinas</h1>
        
        <div class="mb-3">
            <a href="DisciplinaController?action=incluir" class="btn btn-success">Incluir Disciplina</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Requisito</th>
                    <th>Ementa</th>
                    <th>Carga Horária</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Disciplina> listaDisciplinas = (List<Disciplina>) request.getAttribute("listaDisciplinas");
                    if (listaDisciplinas != null && !listaDisciplinas.isEmpty()) {
                        for (Disciplina disciplina : listaDisciplinas) {
                %>
                <tr>
                    <td><%= disciplina.getNome() %></td>
                    <td><%= disciplina.getRequisito() %></td>
                    <td><%= disciplina.getEmenta() %></td>
                    <td><%= disciplina.getCarga_horaria() %></td>
                    <td>
                        <!-- Botão de editar -->
                        <a href="DisciplinaController?action=alterar&id=<%= disciplina.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <!-- Botão de excluir -->
                        <a href="DisciplinaController?action=excluir&id=<%= disciplina.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhuma disciplina encontrada.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
