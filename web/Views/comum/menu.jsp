<%@page import="Entidade.Professor, Entidade.Administrador, Entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="Entidade.Administrador" %>
<%
    // Recupera o usuário logado da sessão
    String usuarioTipo = (String) session.getAttribute("usuarioTipo"); // "administrador", "aluno" ou "professor"
    Object usuarioLogado = (Object) session.getAttribute("usuario");
    int id = 0;
        if (usuarioLogado instanceof Professor) {
        Professor professor = (Professor) usuarioLogado;
        id = professor.getId();
    } else if (usuarioLogado instanceof Aluno) {
        Aluno aluno = (Aluno) usuarioLogado;
        id =aluno.getId();
    }
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid justify-content-start">        
        <!-- Adiciona o link Dashboard baseado no tipo de usuário -->
        <% if (usuarioTipo != null) { 
            switch (usuarioTipo) {
                case "administrador":
        %>
                    <a
                       class="navbar-brand text-primary" 
                       href="/Escola/Views/privado/admin/adminDashboard.jsp"
                    >
                        Dashboard Administrador
                    </a>
        <% 
                    break;
                case "professor":
        %>
                    <a
                       class="navbar-brand text-primary" 
                       href="ProfessorController?action=dashboard&id=<%= id %>"
                    >
                        Dashboard Professor
                    </a>
        <% 
                    break;
                case "aluno":
        %>
                    <a
                       class="navbar-brand text-primary" 
                       href="AlunoController?action=dashboard&id=<%= id %>"
                    >
                        Dashboard Aluno
                    </a>
        <% 
                    break;
            } 
        } 
        %>

        <a class="nav-link text-primary" href="/Escola/privado/logOut">Sair</a>
    </div>
</nav>
