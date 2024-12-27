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
        <h2 class="text-center mb-4">Selecione uma Turma</h2>

        <%
            int profId = (Integer) request.getAttribute("id");
            Map<String, List<Turmas>> turmasAgrupadas = (Map<String, List<Turmas>>) request.getAttribute("turmasAgrupadas");
        %>

        <div class="accordion" id="accordionTurmas">
            <%
                for (Map.Entry<String, List<Turmas>> entry : turmasAgrupadas.entrySet()) {
                    String codigoTurma = entry.getKey();
                    List<Turmas> turmas = entry.getValue();
            %>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-<%= codigoTurma %>">
                    <div class="d-flex align-items-center">
                        <!-- Botão Accordion -->
                        <button class="accordion-button collapsed flex-grow-1 text-start me-3" type="button" 
                                data-bs-toggle="collapse" data-bs-target="#collapse-<%= codigoTurma %>" 
                                aria-expanded="false" aria-controls="collapse-<%= codigoTurma %>">
                            Turma: <%= codigoTurma %>
                        </button>
                    </div>
                </h2>
                <div id="collapse-<%= codigoTurma %>" 
                     class="accordion-collapse collapse" 
                     aria-labelledby="heading-<%= codigoTurma %>" 
                     data-bs-parent="#accordionTurmas">
                    <div class="accordion-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Aluno</th>
                                    <th>Nota</th>
                                    <th>Disciplina</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Turmas turma : turmas) {
                                %>
                                <tr>
                                    <td><%= turma.getAluno().getNome() %></td>
                                    <td><%= turma.getNota() %></td>
                                    <td><%= turma.getDisciplina().getNome() %></td>
                                    <td>
                                        <a href="ProfessorController?action=editarNota&turmaId=<%= turma.getId()%>"
                                           class="btn btn-primary btn-sm">
                                            Editar Nota
                                        </a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
