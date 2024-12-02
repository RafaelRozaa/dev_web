<%@page contentType="text/html" pageEncoding="UTF-8" import="Entidade.Administrador" %>
<%
    // Recupera o usuário logado da sessão
    String usuarioTipo = (String) session.getAttribute("usuarioTipo"); // "administrador", "aluno" ou "professor"
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid justify-content-start">
        <a class="navbar-brand text-primary" href="/Escola/home">Home</a>
        
        <!-- Adiciona o link Dashboard baseado no tipo de usuário -->
        <% if (usuarioTipo != null) { 
            switch (usuarioTipo) {
                case "administrador":
        %>
                    <a
                       class="nav-link text-primary" 
                       href="/Escola/Views/privado/admin/adminDashboard.jsp"
                    >
                        Dashboard Administrador
                    </a>
        <% 
                    break;
                case "professor":
        %>
                    <a
                       class="nav-link text-primary" 
                       href="/Escola/Views/privado/prof/profDashboard.jsp"
                    >
                        Dashboard Professor
                    </a>
        <% 
                    break;
                case "aluno":
        %>
                    <a
                       class="nav-link text-primary" 
                       href="/Escola/Views/privado/aluno/alunoDashboard.jsp"
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
