<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entidade.Turmas" %>
<!DOCTYPE html>
<html>
<head>
    <title>Relatório da Turma</title>
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</head>
    
<body>
    <%
        // Recupera o atributo "relatorio", que contém as turmas (uma única turma)
        List<Turmas> relatorio = (List<Turmas>) request.getAttribute("relatorio");
        
        if (relatorio != null && !relatorio.isEmpty()) {
            // Como todas as tuplas são da mesma turma, podemos pegar os dados da primeira turma
            Turmas turma = relatorio.get(0);
            int numeroInscritos = relatorio.size();  // Número de inscritos
            int numeroVagas = 2; // Exemplo: número fixo de vagas na turma

            // Calculando a nota média manualmente
            double somaNotas = 0.0;
            for (Turmas t : relatorio) {
                somaNotas += t.getNota();
            }
            double notaMedia = somaNotas / numeroInscritos; // Média das notas

    %>
    <jsp:include page="/Views/comum/menu.jsp" />
    <div class="container mt-5">
        <h2 class="mb-3">Relatório da Turma <%= turma.getCodigo_turma() %></h2>
        <p><strong>Disciplina:</strong> <%= turma.getDisciplina().getNome() %></p>
        <p><strong>Professor:</strong> <%= turma.getProfessor().getNome() %></p>
        <p><strong>Número de Inscritos/Vagas:</strong> <%= numeroInscritos %>/<%= numeroVagas %></p>
        <p><strong>Nota Média da Turma:</strong> <%= String.format("%.2f", notaMedia) %></p>
        
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Aluno</th>
                        <th>Nota</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Exibe os alunos e as notas para a turma
                        for (Turmas t : relatorio) {
                    %>
                        <tr>
                            <td><%= t.getAluno().getNome() %></td>
                            <td><%= t.getNota() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    <%
        } else {
    %>
        <p class="text-center text-danger">Nenhuma turma encontrada.</p>
    <%
        }
    %>
    </div>
</body>
</html>
