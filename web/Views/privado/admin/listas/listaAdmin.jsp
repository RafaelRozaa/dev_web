<%@page import="java.util.List"%>
<%@page import="Entidade.Administrador"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Administradores</title>
    <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />
    
    <div class="container mt-5">
        <h1>Lista de Administradores</h1>
        
        <div class="mb-3">
            <a href="AdminController?action=incluir" class="btn btn-success">Incluir Administrador</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Senha</th>
                    <th>Endereço</th>
                    <th>Aprovado</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Administrador> listaAdmins = (List<Administrador>) request.getAttribute("listaAdmins");
                    if (listaAdmins != null && !listaAdmins.isEmpty()) {
                        for (Administrador admin : listaAdmins) {
                %>
                <tr>
                    <td><%= admin.getNome() %></td>
                    <td><%= admin.getCpf() %></td>
                    <td><%= admin.getSenha() %></td>
                    <td><%= admin.getEndereco() %></td>
                    <td><%= admin.getAprovado() != null && admin.getAprovado().equals("y") ? "Sim" : "Não" %></td>
                    <td>
                        <!-- Botão de editar -->
                        <a href="AdminController?action=alterar&id=<%= admin.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <!-- Botão de excluir -->
                        <a href="AdminController?action=excluir&id=<%= admin.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="6" class="text-center">Nenhum administrador encontrado.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
