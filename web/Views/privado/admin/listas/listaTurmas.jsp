<%@page import="java.util.List"%>
<%@page import="Entidade.Turmas"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Turmas</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    
    <div class="container mt-5">
        <h1>Lista de Turmas</h1>
        
        <div class="mb-3">
            <a href="TurmasController?action=incluir" class="btn btn-success">Incluir Turma</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Código da Turma</th>
                    <th>Código do Professor</th>
                    <th>Nome do Professor</th>
                    <th>Código da Disciplina</th>
                    <th>Nome da Disciplina</th>
                    <th>Código do Aluno</th>
                    <th>Nome do Aluno</th>
                    <th>Nota</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Turmas> listaTurmas = (List<Turmas>) request.getAttribute("listaTurmas");
                    if (listaTurmas != null && !listaTurmas.isEmpty()) {
                        for (Turmas turma : listaTurmas) {
                %>
                <tr>
                    <td><%= turma.getCodigo_turma() %></td>
                    <td><%= turma.getProfessor_id() %></td>
                    <td><%= turma.getNome_Professor() %></td>
                    <td><%= turma.getDisciplina_id() %></td>
                    <td><%= turma.getNome_Disciplina() %></td>
                    <td><%= turma.getAluno_id() %></td>
                    <td><%= turma.getNome_Aluno() %></td>
                    <td><%= turma.getNota() %></td>
                    <td>
                        <!-- Botão de editar -->
                        <a href="TurmasController?action=alterar&id=<%= turma.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <!-- Botão de excluir -->
                        <a href="TurmasController?action=excluir&id=<%= turma.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="6" class="text-center">Nenhuma turma encontrada.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
