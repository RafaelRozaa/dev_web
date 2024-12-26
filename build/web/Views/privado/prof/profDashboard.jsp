<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Entidade.Professor"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor - Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />

    <!-- Seção de Cards -->
    <section class="container mt-4">
        <h2 class="text-center mb-4">Dashboard do Professor</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Lançar Notas</h5>
                        <p class="card-text">Adicione e/ou edite as notas dos alunos de suas turmas</p>
                        <a href="#" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Listar notas</h5>
                        <p class="card-text">Consulte as notas dos alunos por disciplina/turma</p>
                        <a href="#" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

