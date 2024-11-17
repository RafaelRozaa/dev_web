<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Escola - Home</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
</head>
<body>

    <!-- Banner de Boas-Vindas -->
    <header class="jumbotron text-center bg-primary text-white">
        <h1 class="display-4">Bem-vindo à Escola</h1>
        <p class="lead">Acesse a plataforma como Administrador, Professor ou Aluno</p>
    </header>

    <!-- Seção de Escolha de Login -->
    <section class="container my-5">
        <h2 class="text-center mb-4">Selecione seu Perfil</h2>
        <div class="row text-center">
            <!-- Card de Login do Administrador -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Administrador</h5>
                        <p class="card-text">Acesse o sistema para gerenciar alunos, professores e disciplinas.</p>
                        <a href="/Escola/AutenticaController?login=admin" class="btn btn-primary">Login de Administrador</a>
                    </div>
                </div>
            </div>

            <!-- Card de Login do Professor -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Professor</h5>
                        <p class="card-text">Acesse o sistema para gerenciar suas turmas e lançar notas.</p>
                        <a href="/Escola/AutenticaController?login=prof" class="btn btn-primary">Login de Professor</a>
                    </div>
                </div>
            </div>

            <!-- Card de Login do Aluno -->
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Aluno</h5>
                        <p class="card-text">Acesse o sistema para visualizar disciplinas e notas.</p>
                        <a href="/Escola/AutenticaController?login=aluno" class="btn btn-primary">Login de Aluno</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS -->
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>

