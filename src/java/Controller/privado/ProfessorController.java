package Controller.privado;

import Entidade.Professor;
import Entidade.Turmas;
import Model.ProfessorDAO;
import Model.TurmasDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfessorController", urlPatterns = {"/privado/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher rd;
        ProfessorDAO dao = new ProfessorDAO();
        TurmasDAO turmaDao = new TurmasDAO();

        try {
            switch (action) {
                case "listar":
                    request.setAttribute("listaProfessores", dao.ListaDeProfessores());
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaProfessor.jsp");
                    rd.forward(request, response);
                    return;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("professor", dao.getProfessor(id));
                    request.setAttribute("action", action);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("professor", dao.getProfessor(id));
                    request.setAttribute("action", action);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    break;
                case "editarNota":
                    int turmaId = Integer.parseInt(request.getParameter("turmaId"));
                    Turmas turma = turmaDao.getTurma(turmaId);
                    request.setAttribute("turma", turma);                    
                    rd = request.getRequestDispatcher("/Views/privado/prof/formNota.jsp");
                    rd.forward(request, response);
                    return;
                case "dashboard":
                    int profId = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("turmasAgrupadas", turmaDao.turmasAgrupadasPorCodigo(profId));
                    request.setAttribute("id",profId);
                    rd = request.getRequestDispatcher("/Views/privado/prof/profDashboard.jsp");
                    rd.forward(request, response);
                    return;
            }
            rd = request.getRequestDispatcher("/Views/privado/admin/forms/formProfessor.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btEnviar = request.getParameter("btEnviar");
        Professor professor = new Professor(null, null, null, null);
        professor.setId(Integer.parseInt(request.getParameter("id")));
        professor.setNome(request.getParameter("nome"));
        professor.setEmail(request.getParameter("email"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setSenha(request.getParameter("senha"));

        ProfessorDAO dao = new ProfessorDAO();
        TurmasDAO turmaDao = new TurmasDAO();
        RequestDispatcher rd;

        try {
            switch (btEnviar) {
                case "criar":
                    dao.Inserir(professor);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;
                case "alterar":
                    dao.Alterar(professor);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "excluir":
                    dao.Excluir(professor.getId());
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
                case "editarNota":
                    int turmaId = Integer.parseInt(request.getParameter("turmaId"));
                    int profId = Integer.parseInt(request.getParameter("id"));
                    float novaNota = Float.parseFloat(request.getParameter("nota"));
                    turmaDao.editarNota(turmaId, novaNota);
                    
                    response.sendRedirect("ProfessorController?action=dashboard&id=" + profId);
                    return;
            }

            request.setAttribute("link", "ProfessorController?action=listar");
            rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha na query para postar formulário", e);
        }
    }
}
