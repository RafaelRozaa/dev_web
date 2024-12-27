package Controller.privado;

import Entidade.Turmas;
import Entidade.Professor;
import Model.ProfessorDAO;
import Entidade.Disciplina;
import Model.DisciplinaDAO;
import Entidade.Aluno;
import Model.AlunoDAO;
import Model.TurmasDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                case "relatorio":
                    request.setAttribute("turmasPorCod", dao.TurmasPorCod());
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaRelatorios.jsp");
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

        RequestDispatcher rd;

        String btEnviar = request.getParameter("btEnviar");

        Turmas turma = new Turmas();

        String action = request.getParameter("action");

        turma.setId(Integer.parseInt(request.getParameter("id")));
        
        if ("listar".equals(action) || "incluir".equals(action)|| "alterar".equals(action)) {
            Professor professor = new Professor();
            Aluno aluno = new Aluno();
            Disciplina disciplina = new Disciplina();
            professor.setId(Integer.parseInt(request.getParameter("professor_id")));
            turma.setProfessor(professor);

            disciplina.setId(Integer.parseInt(request.getParameter("disciplina_id")));
            turma.setDisciplina(disciplina);
            if (!request.getParameter("aluno_id").isEmpty()) {
                aluno.setId(Integer.parseInt(request.getParameter("aluno_id")));
                turma.setAluno(aluno);
            }

            turma.setCodigo_turma(request.getParameter("codigo_turma"));
            turma.setNota(Float.parseFloat(request.getParameter("nota")));

            ProfessorDAO professorDao = new ProfessorDAO();
            Professor professorObtido = new Professor();

            try {
                professorObtido = professorDao.getProfessor(professor.getId());
            } catch (Exception ex) {
                Logger.getLogger(TurmasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (professorObtido.getId() == 0) {
                request.setAttribute("msgError", "Professor não existe");
                request.setAttribute("action", action);
                rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                rd.forward(request, response);
            }

            DisciplinaDAO disciplinaDao = new DisciplinaDAO();
            Disciplina disciplinaObtido = new Disciplina();

            try {
                disciplinaObtido = disciplinaDao.getDisciplina(disciplina.getId());
            } catch (Exception ex) {
                Logger.getLogger(TurmasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (disciplinaObtido.getId() == 0) {
                request.setAttribute("msgError", "Disciplina não existe");
                request.setAttribute("action", action);
                rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                rd.forward(request, response);
            }

            AlunoDAO alunoDao = new AlunoDAO();
            Aluno alunoObtido = new Aluno();

            try {
                alunoObtido = alunoDao.getAluno(aluno.getId());
            } catch (Exception ex) {
                Logger.getLogger(TurmasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (alunoObtido.getId() == 0) {
                request.setAttribute("msgError", "Aluno não existe");
                request.setAttribute("action", action);
                rd = request.getRequestDispatcher("/Views/privado/admin/forms/formTurmas.jsp");
                rd.forward(request, response);
            }
        }
        TurmasDAO dao = new TurmasDAO();

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
                case "relatar":
                    turma.setCodigo_turma(request.getParameter("codigo_turma"));
                    request.setAttribute("relatorio",dao.TurmaRelatorio(turma.getCodigo_turma()));
                    rd = request.getRequestDispatcher("/Views/privado/admin/relatorio.jsp");
                    rd.forward(request, response);
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
