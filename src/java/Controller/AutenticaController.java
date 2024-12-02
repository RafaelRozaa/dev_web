package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.AdministradorDAO;
import Entidade.Administrador;
import Entidade.Aluno;
import Model.AlunoDAO;

@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/Views/autenticacao/formLogin.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // Pegando os parâmetros do request
        String login = request.getParameter("login");
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // Dados não foram preenchidos, retorna ao formulário
            request.setAttribute("msgError", "CPF e/ou senha não foram preenchidos");
            rd = request.getRequestDispatcher("/Views/autenticacao/formLogin.jsp");
            rd.forward(request, response);
        } else {
            switch (login) {
                case "admin":
                Administrador administradorObtido = new Administrador();
                Administrador admin = new Administrador(cpf_user, senha_user);
                AdministradorDAO adminDAO = new AdministradorDAO();
                try {
                    administradorObtido = adminDAO.Logar(admin);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println(cpf_user);
                    System.out.println(senha_user);
                    throw new RuntimeException("Falha na query para Logar");
                }

                if (administradorObtido.getId() != 0 && "y".equals(administradorObtido.getAprovado())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("administrador", administradorObtido);
                        session.setAttribute("administrador", administradorObtido);
                        session.setAttribute("usuarioTipo", "administrador");
                        rd = request.getRequestDispatcher("/Views/privado/admin/adminDashboard.jsp");
                } else {
                    request.setAttribute("msgError", "CPF e/ou senha incorreto");
                    rd = request.getRequestDispatcher("/Views/autenticacao/formLogin.jsp");
                }
                    rd.forward(request, response);
                    break;
                    
                case "prof":
                    // Implementação para "prof"
                    break;
                    
                case "aluno":
                Aluno alunoObtido = new Aluno();
                Aluno aluno = new Aluno(cpf_user, senha_user);
                AlunoDAO alunoDAO = new AlunoDAO();
                try {
                    alunoObtido = alunoDAO.Logar(aluno);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println(cpf_user);
                    System.out.println(senha_user);
                    throw new RuntimeException("Falha na query para Logar");
                }

                if (alunoObtido.getId() != 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("aluno", alunoObtido);
                        session.setAttribute("aluno", alunoObtido);
                        session.setAttribute("usuarioTipo", "aluno");
                        rd = request.getRequestDispatcher("/Views/privado/aluno/alunoDashboard.jsp");
                } else {
                    request.setAttribute("msgError", "CPF e/ou senha incorreto");
                    rd = request.getRequestDispatcher("/Views/autenticacao/formLogin.jsp");
                }
                    rd.forward(request, response);
                    break;
            }
        }
    }
}
