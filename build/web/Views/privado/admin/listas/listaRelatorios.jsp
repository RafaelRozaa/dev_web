<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Entidade.Turmas"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
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
            // Recupera o atributo "turmasPorCod", que contém a lista de turmas
            List<Turmas> turmas = (List<Turmas>) request.getAttribute("turmasPorCod");

            // Variáveis para armazenar valores analíticos
            int totalTurmas = 0;
            int totalDisciplinas = 0;
            Map<String, Integer> turmasPorDisciplina = new HashMap<>();

            if (turmas != null && !turmas.isEmpty()) {
                // Contagem do número total de turmas e de turmas por disciplina
                totalTurmas = turmas.size();
                Set<String> disciplinasSet = new HashSet<>();
                for (Turmas turma : turmas) {
                    String nomeDisciplina = turma.getDisciplina().getNome();
                    turmasPorDisciplina.put(nomeDisciplina, turmasPorDisciplina.getOrDefault(nomeDisciplina, 0) + 1);
                    disciplinasSet.add(nomeDisciplina);
                }
                totalDisciplinas = disciplinasSet.size(); // Número total de disciplinas
            }
        %>

        <!-- Exibição dos valores analíticos -->
        <div class="mb-4">
            <p><strong>Total de Turma(s):</strong> <%= totalTurmas %></p>
            <p><strong>Total de Disciplina(s):</strong> <%= totalDisciplinas %></p>
            <p><strong>Turmas por disciplina:</strong></p>
            <ul>
                <%
                    // Exibe o número de turmas para cada disciplina
                    for (Map.Entry<String, Integer> entry : turmasPorDisciplina.entrySet()) {
                %>
                <li><strong><%= entry.getKey() %>:</strong> <%= entry.getValue() %> turma(s)</li>
                <%
                    }
                %>
            </ul>
        </div>

        <form action="TurmasController" method="post">
            <input type="hidden" name="id" value="0">
            <div class="text-left mt-4">
                <button type="submit" name="btEnviar" value="relatar" class="btn btn-primary">Gerar Relatório</button>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Selecionar</th>
                            <th>Código da Turma</th>
                            <th>Nome da Disciplina</th>
                            <th>Nome do Professor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (turmas != null && !turmas.isEmpty()) {
                                for (Turmas turma : turmas) {
                        %>
                        <tr>
                            <td>
                                <input 
                                    type="radio" 
                                    name="codigo_turma" 
                                    value="<%= turma.getCodigo_turma() %>" 
                                    required>
                            </td>
                            <td><%= turma.getCodigo_turma() %></td>
                            <td><%= turma.getDisciplina().getNome() %></td>
                            <td><%= turma.getProfessor().getNome() %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="6" class="text-center">Nenhuma turma disponível.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</body>
</html>
