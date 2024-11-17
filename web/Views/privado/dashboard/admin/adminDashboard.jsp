<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Entidade.Administrador"%>
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
    <!-- Navbar -->
    <%
        Administrador administrador = (Administrador) session.getAttribute("administrador");
        String nome = administrador.getNome();
        %>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
          <span class="navbar-brand">Bem Vindo <%=nome%></span>
          <a class="nav-link ms-3" href="/Escola/privado/logOut">Sair</a>
        </div>
    </div>
    </nav>

    <!-- Seção de Cards -->
    <section class="container my-5">
        <h2 class="text-center mb-4">Dashboard do Administrador</h2>
        <div class="row">
            <!-- Card de Cadastro de Aluno -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Cadastro de Aluno</h5>
                        <p class="card-text">Cadastre novos alunos no sistema.</p>
                        <a href="/privado/RegistrarController?registro=aluno" class="btn btn-primary">Cadastrar Aluno</a>
                    </div>
                </div>
            </div>

            <!-- Card de Cadastro de Professor -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Cadastro de Professor</h5>
                        <p class="card-text">Cadastre novos professores no sistema.</p>
                        <a href="/privado/RegistrarController?registro=prof" class="btn btn-primary">Cadastrar Professor</a>
                    </div>
                </div>
            </div>

            <!-- Card de Cadastro de Administrador -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Cadastro de Administrador</h5>
                        <p class="card-text">Cadastre novos administradores no sistema.</p>
                        <a href="/privado/RegistrarController?registro=admin" class="btn btn-primary">Cadastrar Administrador</a>
                    </div>
                </div>
            </div>

            <!-- Card de Cadastro de Turma -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Cadastro de Turma</h5>
                        <p class="card-text">Gerencie as turmas disponíveis no sistema.</p>
                        <a href="/privado/RegistrarController?registro=turma" class="btn btn-primary">Cadastrar Turma</a>
                    </div>
                </div>
            </div>

            <!-- Card de Cadastro de Disciplina -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body text-center">
                        <h5 class="card-title">Cadastro de Disciplina</h5>
                        <p class="card-text">Gerencie as disciplinas oferecidas no sistema.</p>
                        <a href="/privado/RegistrarController?registro=disc" class="btn btn-primary">Cadastrar Disciplina</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="js/jquery.min.js"></script>
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>