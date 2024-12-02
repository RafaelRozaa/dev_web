package Controller.privado;

import Entidade.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.AdministradorDAO;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "AdminController", urlPatterns = {"/privado/AdminController"})
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        RequestDispatcher rd;
        AdministradorDAO dao = new AdministradorDAO();
        
        try {
            
            switch (action) {
                case "listar":
                    request.setAttribute("listaAdmins", dao.ListaDeAdministrador());
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaAdmin.jsp");                    
                    rd.forward(request, response);
                    break;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("admin", dao.getAdministrador(id));
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAdmin.jsp");
                    rd.forward(request, response);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("admin", dao.getAdministrador(id));
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAdmin.jsp");
                    rd.forward(request, response);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAdmin.jsp");
                    rd.forward(request, response);
                default:
                    response.sendRedirect("AdminController?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AdminController?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btEnviar = request.getParameter("btEnviar");
        Administrador admin = new Administrador();
        admin.setId(Integer.parseInt(request.getParameter("id")));
        admin.setNome(request.getParameter("nome"));
        admin.setCpf(request.getParameter("cpf"));
        admin.setSenha(request.getParameter("senha"));
        admin.setEndereco(request.getParameter("endereco"));
        admin.setAprovado(request.getParameter("aprovado"));
        AdministradorDAO dao = new AdministradorDAO();
        RequestDispatcher rd;
        try {
            switch (btEnviar) {
                case "criar":
                    dao.Inserir(admin);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    
                    break;
                case "alterar":
                    dao.Alterar(admin);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "excluir":
                    dao.Excluir(admin.getId());
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }
            
                request.setAttribute("link", "AdminController?action=listar");
                rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
                rd.forward(request, response);
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query para postar formulário");
        }
    }
}