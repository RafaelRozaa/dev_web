package Controller.privado;

import Entidade.Turmas;
import Model.TurmasDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TurmasController", urlPatterns = {"/privado/TurmasController"})
public class TurmasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher rd;
        TurmasDAO dao = new TurmasDAO();

        try {
            switch (action) {
                case "listar":
                    request.setAttribute("listaTurmas", dao.ListaDeTurmas());
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaTurmas.jsp");
                    rd.forward(request, response);
                    break;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("turma", dao.getTurma(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                    rd.forward(request, response);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("turma", dao.getTurma(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                    rd.forward(request, response);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    response.sendRedirect("TurmasController?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("TurmasController?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btEnviar = request.getParameter("btEnviar");
        Turmas turma = new Turmas(0, 0, 0, null, null);
        turma.setId(Integer.parseInt(request.getParameter("id")));
        turma.setProfessor_id(Integer.parseInt(request.getParameter("professor_id")));
        turma.setDisciplina_id(Integer.parseInt(request.getParameter("disciplina_id")));
        turma.setAluno_id(Integer.parseInt(request.getParameter("aluno_id")));
        turma.setCodigo_turma(request.getParameter("codigo_turma"));
        turma.setNota(Float.parseFloat(request.getParameter("nota")));

        TurmasDAO dao = new TurmasDAO();
        RequestDispatcher rd;

        try {
            switch (btEnviar) {
                case "criar":
                    dao.Inserir(turma);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;
                case "alterar":
                    dao.Alterar(turma);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "excluir":
                    dao.Excluir(turma.getId());
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }

            request.setAttribute("link", "TurmasController?action=listar");
            rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query para postar formulário", ex);
        }
    }
}
