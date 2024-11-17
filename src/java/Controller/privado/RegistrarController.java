package Controller.privado;

import Entidade.Administrador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.AdministradorDAO;

// Pega os dados de todas as views de registro, verifica se são validos e usa o dao pra fazer o envio
@WebServlet(name = "RegistrarController", urlPatterns = {"/privado/RegistrarController"})
public class RegistrarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String registro = request.getParameter("registro");
        switch(registro) {
            case "aluno":
                rd = request.getRequestDispatcher("/Views/registro/alunoReg.jsp"); 
                rd.forward(request, response);
                break;
            case "prof":
                rd = request.getRequestDispatcher("/Views/registro/profReg.jsp"); 
                rd.forward(request, response);
                break;
            case "admin":
                rd = request.getRequestDispatcher("/Views/registro/adminReg.jsp"); 
                rd.forward(request, response);
                break;
            case "disc":
                rd = request.getRequestDispatcher("/Views/registro/discReg.jsp"); 
                rd.forward(request, response);
                break;
            case "turma":
                rd = request.getRequestDispatcher("/Views/registro/turmaReg.jsp"); 
                rd.forward(request, response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        request.setAttribute("msgOperacaoRealizada", "Operação não implementada");
        request.setAttribute("link", "home");
        rd = request.getRequestDispatcher("/Views/registro/adminReg.jsp");
        rd.forward(request, response);

    }

}
