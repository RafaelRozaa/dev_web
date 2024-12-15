<%@page contentType="text/html" pageEncoding="ISO-8859-9"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Escola - Login</title>
        <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
    </head>
    <body>
        <%
            String login = (String) request.getParameter("login");
            String msgError = (String) request.getAttribute("msgError");
        %>
        <section class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card p-4 shadow" style="width: 100%; max-width: 400px;">
            <h2 class="text-center mb-4">Login</h2>
            <form action="/Escola/AutenticaController?login=<%=login %>" method="post">         
                <!-- CPF -->
                <div class="form-group mb-3">
                    <label for="cpf">CPF</label>
                    <input type="text" class="form-control" id="cpf" value='249.252.810-38' name="cpf" required placeholder="Digite seu CPF">
                </div>
                
                <!-- Senha -->
                <div class="form-group">
                    <label for="senha" value='111'>Senha</label>
                    <input type="password" class="form-control" value='111' id="senha" name="senha" required placeholder="Digite sua senha">
                </div>
                
                <button type="submit" class="btn btn-primary btn-block mt-3">Entrar</button>
            </form>
            <%-- Exibe mensagem de erro, caso exista --%>
            <%
                if ((msgError != null) && (!msgError.isEmpty())) {
            %>
                <div class="alert alert-danger mt-3" role="alert">
                    <%= msgError %>
                </div>
            <% } %>
        </div>
    </section>
        
        <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
