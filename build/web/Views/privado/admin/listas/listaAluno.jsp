<%@page import="java.util.List"%>
<%@page import="Entidade.Aluno"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Alunos</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    
    <div class="container mt-5">
        <h1>Lista de Alunos</h1>
        
        <div class="mb-3">
            <a href="AlunoController?action=incluir" class="btn btn-success">Incluir Aluno</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Celular</th>
                    <th>CPF</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Aluno> listaAlunos = (List<Aluno>) request.getAttribute("listaAlunos");
                    if (listaAlunos != null && !listaAlunos.isEmpty()) {
                        for (Aluno aluno : listaAlunos) {
                %>
                <tr>
                    <td><%= aluno.getNome() %></td>
                    <td><%= aluno.getEmail() %></td>
                    <td><%= aluno.getCelular() %></td>
                    <td><%= aluno.getCpf() %></td>
                    <td>
                        <!-- Botão de editar -->
                        <a href="AlunoController?action=alterar&id=<%= aluno.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <!-- Botão de excluir -->
                        <a href="AlunoController?action=excluir&id=<%= aluno.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="5" class="text-center">Nenhum aluno encontrado.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
