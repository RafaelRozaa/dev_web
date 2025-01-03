<%@page import="Entidade.Professor"%>
<%@page import="Model.TurmasDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="Entidade.Turmas"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Seleção de Turmas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
        <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="/Views/comum/menu.jsp" />

        <div class="container mt-4">
            <h2 class="text-center mb-4">Turmas disponíveis para inscrição</h2>
            <div class="accordion" id="accordionTurmas">
                <table class="table table-striped">
                    <thead>
                        <tr>                                   
                            <th>Turma</th>
                            <th>Disciplina</th>
                            <th>Professor</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                
                            int alunoId = (Integer) request.getAttribute("id");
                            Map<String, List<Turmas>> turmasAgrupadas = (Map<String, List<Turmas>>) request.getAttribute("turmasAgrupadas");
                            List<Turmas> turmasPorAluno = (List<Turmas>) request.getAttribute("turmasPorAluno");
                            for (Map.Entry<String, List<Turmas>> entry : turmasAgrupadas.entrySet()) {
                                String codigoTurma = entry.getKey();
                                List<Turmas> turmas = entry.getValue();
                                Turmas turma = turmas.get(0);
                        %>
                        <tr>
                            <td><%= turma.getCodigo_turma()%></td>
                            <td><%= turma.getDisciplina().getNome()%></td>
                            <td><%= turma.getProfessor().getNome()%></td>             
                            <td>
                                <a href="AlunoController?action=inscrever&codTurma=<%= turma.getCodigo_turma()%>&aluno=<%= alunoId%>&disciplina=<%= turma.getDisciplina().getId()%>&professor=<%= turma.getProfessor().getId()%>"
                                   class="btn btn-primary btn-sm">
                                    Inscreva-se
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <h2 class="text-center mb-4 mt-4">Turmas em que está inscrito</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>                                   
                            <th>Turma</th>
                            <th>Disciplina</th>
                            <th>Professor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Turmas turma : turmasPorAluno) {
                        %>
                        <tr>
                            <td><%= turma.getCodigo_turma()%></td>
                            <td><%= turma.getDisciplina().getNome()%></td>
                            <td><%= turma.getProfessor().getNome()%></td>             
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
