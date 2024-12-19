<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidade.Administrador"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário Administrador</title>
        <link href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/Views/comum/menu.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <%
                        Administrador admin = (Administrador) request.getAttribute("admin");
                        String action = (String) request.getAttribute("action");
                        if (admin == null) {
                            admin = new Administrador();
                        }
                    %>
                    <h1 class="mb-2">
                        <% if ("incluir".equals(action)) { %>
                        Criar Administrador
                        <% } else if ("alterar".equals(action)) { %>
                        Alterar Administrador
                        <% } else if ("excluir".equals(action)) { %>
                        Excluir Administrador
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

                    <!-- Formulário de administrador -->
                    <form action="AdminController" method="POST">
                        <!-- Campo oculto para enviar o ID -->
                        <input type="hidden" name="id" value="<%= admin.getId()%>">

                        <!-- Nome -->
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" id="nome" required  
                                   class="form-control"
                                   value='<%= "excluir".equals(action) || "alterar".equals(action) ? admin.getNome() : ""%>'
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- CPF -->
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" id="cpf" required" 
                                   class="form-control"
                                    value=<%= "excluir".equals(action) || "alterar".equals(action) ? admin.getCpf(): ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Endereço -->
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" id="endereco" required" 
                                   class="form-control"
                                    value='<%= "excluir".equals(action) || "alterar".equals(action) ? admin.getEndereco(): ""%>'
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Senha -->
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" id="senha" required" 
                                   class="form-control"
                                    value=<%= "excluir".equals(action) || "alterar".equals(action) ? admin.getSenha(): ""%>
                                   <%= "excluir".equals(action) ? "readonly" : ""%>>
                        </div>

                        <!-- Aprovado -->
                        <div class="mb-3">
                            <label class="form-label">Aprovado</label>
                            <div>
                                <!-- Botão de Rádio para "Sim" -->
                                <input type="radio" class="btn-check" id="aprovadoSim<%= admin.getId()%>" name="aprovado" value="y" 
                                       <%= admin.getAprovado().equals("y") ? "checked" : ""%> 
                                       <%= "excluir".equals(action) ? "disabled" : ""%>>
                                <label class="btn btn-outline-success" for="aprovadoSim<%= admin.getId()%>">Sim</label>

                                <!-- Botão de Rádio para "Não" -->
                                <input type="radio" class="btn-check" id="aprovadoNao<%= admin.getId()%>" name="aprovado" value="n" 
                                       <%= admin.getAprovado().equals("n") ? "checked" : ""%> 
                                       <%= "excluir".equals(action) ? "disabled" : ""%>>
                                <label class="btn btn-outline-danger" for="aprovadoNao<%= admin.getId()%>">Não</label>
                            </div>
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
                            <a href="AdminController?action=listar" class="btn btn-secondary">Cancelar</a>
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
