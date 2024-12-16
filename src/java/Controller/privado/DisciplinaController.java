package Controller.privado;

import Entidade.Disciplina;
import Model.DisciplinaDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplinaController", urlPatterns = {"/privado/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        RequestDispatcher rd;
        DisciplinaDAO dao = new DisciplinaDAO();
        
        try {
            
            switch (action) {
                case "listar":
                    request.setAttribute("listaDisciplinas", dao.ListaDeDisciplinas());
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaDisciplina.jsp");                    
                    rd.forward(request, response);
                    break;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("disciplina", dao.getDisciplina(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formDisciplina.jsp");
                    rd.forward(request, response);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("disciplina", dao.getDisciplina(id));
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formDisciplina.jsp");
                    rd.forward(request, response);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formDisciplina.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    response.sendRedirect("DisciplinaController?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("DisciplinaController?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btEnviar = request.getParameter("btEnviar");
        Disciplina disciplina = new Disciplina(
                request.getParameter("nome"),
                request.getParameter("requisito"),
                request.getParameter("ementa"),
                Integer.parseInt(request.getParameter("carga_horaria"))
        );
        disciplina.setId(Integer.parseInt(request.getParameter("id")));
        
        DisciplinaDAO dao = new DisciplinaDAO();
        RequestDispatcher rd;
        try {
            switch (btEnviar) {
                case "criar":
                    dao.Inserir(disciplina);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;
                case "alterar":
                    dao.Alterar(disciplina);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "excluir":
                    dao.Excluir(disciplina.getId());
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }
            
            request.setAttribute("link", "DisciplinaController?action=listar");
            rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
            rd.forward(request, response);
                
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query para postar formulário");
        }
    }
}
