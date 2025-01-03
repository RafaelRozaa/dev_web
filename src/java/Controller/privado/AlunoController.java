package Controller.privado;

import Entidade.Aluno;
import Model.AlunoDAO;
import Model.TurmasDAO;
import Entidade.Turmas;
import Entidade.Aluno;
import Entidade.Disciplina;
import Entidade.Professor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlunoController", urlPatterns = {"/privado/AlunoController"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher rd;
        AlunoDAO dao = new AlunoDAO();
        TurmasDAO turmaDao = new TurmasDAO();
        try {
            switch (action) {
                case "listar":
                    request.setAttribute("listaAlunos", dao.ListaDeAlunos());
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/listas/listaAluno.jsp");
                    rd.forward(request, response);
                    break;
                case "alterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("aluno", dao.getAluno(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAluno.jsp");
                    rd.forward(request, response);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("aluno", dao.getAluno(id));
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAluno.jsp");
                    rd.forward(request, response);
                    break;
                case "incluir":
                    request.setAttribute("action", action);
                    rd = request.getRequestDispatcher("/Views/privado/admin/forms/formAluno.jsp");
                    rd.forward(request, response);
                    break;
                case "dashboard":
                    int alunoId = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("turmasAgrupadas", turmaDao.turmasAgrupadas(alunoId));
                    request.setAttribute("turmasPorAluno", turmaDao.turmasPorAluno(alunoId));
                    request.setAttribute("id",alunoId);
                    rd = request.getRequestDispatcher("/Views/privado/aluno/alunoDashboard.jsp");
                    rd.forward(request, response);
                    break;
                case "inscrever":
                    Turmas turma = new Turmas();
                    Aluno aluno = new Aluno();
                    Professor professor = new Professor();
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(Integer.parseInt(request.getParameter("disciplina")));
                    professor.setId(Integer.parseInt(request.getParameter("professor")));
                    aluno.setId(Integer.parseInt(request.getParameter("aluno")));
                    turma.setCodigo_turma(request.getParameter("codTurma"));
                    turma.setAluno(aluno);
                    turma.setDisciplina(disciplina);
                    turma.setProfessor(professor);
                    turma.setNota(Float.parseFloat("0"));
                    turmaDao.Inserir(turma);
                    request.setAttribute("msgOperacaoRealizada", "Inscrição realizada com sucesso");
                    
                    request.setAttribute("link", "AlunoController?action=dashboard&id=" + Integer.parseInt(request.getParameter("aluno")));
                    rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    response.sendRedirect("AlunoController?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AlunoController?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btEnviar = request.getParameter("btEnviar");
        Aluno aluno = new Aluno();
        aluno.setId(Integer.parseInt(request.getParameter("id")));
        aluno.setNome(request.getParameter("nome"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setCpf(request.getParameter("cpf"));
        aluno.setSenha(request.getParameter("senha"));
        aluno.setEndereco(request.getParameter("endereco"));
        aluno.setCidade(request.getParameter("cidade"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setBairro(request.getParameter("bairro"));
        aluno.setCep(request.getParameter("cep"));

        AlunoDAO dao = new AlunoDAO();
        RequestDispatcher rd;

        try {
            switch (btEnviar) {
                case "criar":
                    dao.Inserir(aluno);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;
                case "alterar":
                    dao.Alterar(aluno);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "excluir":
                    dao.Excluir(aluno.getId());
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }

            request.setAttribute("link", "AlunoController?action=listar");
            rd = request.getRequestDispatcher("/Views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query para postar formulário", ex);
        }
    }
}
