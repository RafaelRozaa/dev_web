<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Entidade.Administrador"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />

    <!-- Seção de Cards -->
    <section class="container my-5">
        <h2 class="text-center mb-4">Dashboard do Administrador</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Alunos</h5>
                        <p class="card-text">Consulte, inclua, altere e exclua alunos do sistema.</p>
                        <a href="/privado/RegistrarController?registro=aluno" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Professores</h5>
                        <p class="card-text">Consulte, inclua, altere e exclua professores do sistema.</p>
                        <a href="/privado/RegistrarController?registro=prof" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Administradores</h5>
                        <p class="card-text">Consulte, inclua, altere e exclua administradores do sistema.</p>
                        <a href="/Escola/privado/AdminController?action=listar" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Turmas</h5>
                        <p class="card-text">Consulte, inclua, altere e exclua turmas do sistema.</p>
                        <a href="/privado/RegistrarController?registro=turma" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Disciplina</h5>
                        <p class="card-text">Consulte, inclua, altere e exclua disciplinas do sistema.</p>
                        <a href="/privado/RegistrarController?registro=disc" class="btn btn-primary">Entrar</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>