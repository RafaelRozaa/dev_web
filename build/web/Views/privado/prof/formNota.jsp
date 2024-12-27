<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="Entidade.Turmas"%>
<%@page import="java.util.Objects"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <title>Gerenciar Nota</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8080/Escola/Views/bootstrap/bootstrap.min.css">
    <script src="http://localhost:8080/Escola/Views/bootstrap/bootstrap.bundle.min.js"></script>
</head>
<body>
    <jsp:include page="/Views/comum/menu.jsp" />

    <div class="container mt-4">
        <h2 class="text-center mb-4">
            <%
                String turmaId = request.getParameter("turmaId");
                if (turmaId != null) {
                    out.print("Editar Nota");
                } else {
                    out.print("Adicionar Nota");
                }
            %>
        </h2>

        <form action="ProfessorController" method="post">
            <input type="hidden" name="btEnviar" value="<%= turmaId != null ? "editarNota" : "criarNota" %>">
            <input type="hidden" name="turmaId" value="<%= turmaId %>">
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>">

            <!-- Selecione o Aluno -->
            <div class="mb-3">
                <label for="aluno" class="form-label">Escolha o Aluno</label>
                <select class="form-control" id="aluno" name="alunoId" required>
        <% 
            String codigoTurma = request.getParameter("codigoTurma"); // Pega o código da turma enviado
            Map<String, List<Turmas>> turmasAgrupadas = (Map<String, List<Turmas>>) request.getAttribute("turmasAgrupadas");

            if (turmasAgrupadas != null) {
                List<Turmas> turmasDaTurma = turmasAgrupadas.get(codigoTurma); // Obtém a lista de turmas do código selecionado
                
                if (turmasDaTurma != null) {
                    Set<Integer> alunosAdicionados = new HashSet<>(); // Conjunto para evitar duplicação

                    for (Turmas turma : turmasDaTurma) {
                        int alunoId = turma.getAluno().getId();
                        if (!alunosAdicionados.contains(alunoId)) {
                            alunosAdicionados.add(alunoId); // Marca o aluno como já processado
        %>
                            <option value="<%= alunoId %>">
                                <%= turma.getAluno().getNome() %>
                            </option>
        <%
                        }
                    }
                } else {
        %>
                    <option value="">Nenhum aluno disponível</option>
        <%
                }
            }
        %>
                </select>
            </div>

            <!-- Selecione a Disciplina -->
            <div class="mb-3">
                <label for="disciplina" class="form-label">Escolha a Disciplina</label>
                <select class="form-control" id="disciplina" name="disciplinaId" required>
                    <% 
                        for (Map.Entry<String, List<Turmas>> entry : turmasAgrupadas.entrySet()) {
                            for (Turmas turma : entry.getValue()) {
                    %>
                    <option value="<%= turma.getDisciplina().getId() %>">
                        <%= turma.getDisciplina().getNome() %>
                    </option>
                    <% } } %>
                </select>
            </div>

            <!-- Código da Turma -->
            <div class="mb-3">
                <label for="codigoTurma" class="form-label">Código da Turma</label>
                <input type="text" class="form-control" id="codigoTurma" name="codigoTurma" 
                       value="<%= turmaId != null ? request.getAttribute("codigoTurma") : ""%>" 
                       required>
            </div>

            <!-- Nota -->
            <div class="mb-3">
                <label for="nota" class="form-label">Nota</label>
                <input type="number" step="0.1" class="form-control" id="nota" name="nota" 
                       value="<%= turmaId != null ? request.getAttribute("nota") : ""%>"
                       required>
            </div>

            <button type="submit" class="btn btn-primary">
                <%= turmaId != null ? "Salvar Alterações" : "Adicionar Nota" %>
            </button>
            <button onclick="history.back()" class="btn btn-secondary">Cancelar</button>
        </form>
    </div>
</body>
</html>
