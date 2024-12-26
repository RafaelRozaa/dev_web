<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Entidade.Aluno"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aluno - Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />

    <!-- Seção de Cards -->
    <section class="container mt-4">
        <h2 class="text-center mb-4">Dashboard do Aluno</h2>
        <div class="row justify-content-center">
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm" style="min-height: 166px;">
                    <div class="card-body d-flex flex-column justify-content-center text-center">
                        <h5 class="card-title">Inscrição</h5>
                        <p class="card-text">Consulte disciplinas e turmas abertas para inscrição</p>
                        <a href="#" class="btn btn-primary align-self-center">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm" style="min-height: 166px;">
                    <div class="card-body d-flex flex-column justify-content-center text-center">
                        <h5 class="card-title">Histórico de notas</h5>
                        <p class="card-text">Consulte seu histórico de notas</p>
                        <a href="#" class="btn btn-primary align-self-center">Entrar</a>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!-- Bootstrap JS -->
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
