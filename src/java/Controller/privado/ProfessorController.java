package Controller.privado;

import Entidade.Professor;
import Entidade.Turmas;
import Model.ProfessorDAO;
import Model.TurmasDAO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
                    break;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("professor", dao.getProfessor(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formProfessor.jsp");
                    rd.forward(request, response);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("professor", dao.getProfessor(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formProfessor.jsp");
                    rd.forward(request, response);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formProfessor.jsp");
                    rd.forward(request, response);
                    break;
                case "adicionarNota":
                    int profId = Integer.parseInt(request.getParameter("id"));
                    Map<String, List<Turmas>> turmasAgrupadas = turmaDao.buscaTurmasAgrupadasPorCodigo(profId);
                    request.setAttribute("turmasAgrupadas", turmasAgrupadas);
                    request.setAttribute("codigoTurma", request.getParameter("codigoTurma"));
                    request.setAttribute("id", request.getParameter("id"));
                    rd = request.getRequestDispatcher("/Views/privado/prof/formNota.jsp");
                    rd.forward(request, response);
                    break;
                case "editarNota":
                    profId = Integer.parseInt(request.getParameter("id"));
                    turmasAgrupadas = turmaDao.buscaTurmasAgrupadasPorCodigo(profId);
                    request.setAttribute("turmasAgrupadas", turmasAgrupadas);
                    request.setAttribute("turmaId", request.getParameter("turmaId"));
                    request.setAttribute("codigoTurma", request.getParameter("codigoTurma"));
                    request.setAttribute("id", request.getParameter("id"));
                    request.setAttribute("nota", request.getParameter("nota"));
                    rd = request.getRequestDispatcher("/Views/privado/prof/formNota.jsp");
                    rd.forward(request, response);
                    break;
                case "dashboard":
                    profId = Integer.parseInt(request.getParameter("id"));
                    turmasAgrupadas = turmaDao.buscaTurmasAgrupadasPorCodigo(profId);
                    request.setAttribute("turmasAgrupadas", turmasAgrupadas);
                    request.setAttribute("id",profId);
                    rd = request.getRequestDispatcher("/Views/privado/prof/profDashboard.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                case "criarNota":
                    int alunoId = Integer.parseInt(request.getParameter("alunoId"));
                    int disciplinaId = Integer.parseInt(request.getParameter("disciplinaId"));
                    int professorId = Integer.parseInt(request.getParameter("id"));
                    String codigoTurma = request.getParameter("codigoTurma");
                    float nota = Float.parseFloat(request.getParameter("nota"));
                    
                    turmaDao.adicionarNota(alunoId, disciplinaId, professorId, codigoTurma, nota);
                    
                    response.sendRedirect("/Escola/privado/prof/profDashboard.jsp");
                    break;
                case "editarNota":
                    // Lógica para editar nota existente
                    int turmaId = Integer.parseInt(request.getParameter("turmaId"));
                    float novaNota = Float.parseFloat(request.getParameter("nota"));
                    // Atualizar nota no banco (implementar no DAO)
                    turmaDao.editarNota(turmaId, novaNota);
                    response.sendRedirect("/Escola/privado/prof/profDashboard.jsp");
                    break;
            }

            request.setAttribute("link", "ProfessorController?action=listar");
            rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query para postar formulário", ex);
        }
    }
}
