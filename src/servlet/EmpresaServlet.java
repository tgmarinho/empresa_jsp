package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Funcionario;
import pojos.Setor;
import dao.GenericDao;

@WebServlet("/empresaServlet")
public class EmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GenericDao conn;	
	
    public EmpresaServlet() {
        super();
        conn = new GenericDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paginaRequisicao = (String) request.getAttribute("pagina");
		
		
		if("cad_setor".equals(paginaRequisicao)) {
			
			String cad = request.getParameter("cadastrar");
			String exc = request.getParameter("excluir");
			
			if("cadastrar".equals(cad)) {
				int codigo_setor = Integer.parseInt(request.getParameter("cd_setor"));
				String nome = request.getParameter("nome");
				Setor setor = new Setor();
				setor.setId(codigo_setor);
				setor.setNome(nome);
				cadastrar(setor);
				this.conn.buscarTodosFuncionarios();
				RequestDispatcher rd = request.getRequestDispatcher("/cad_setor.jsp");
				rd.forward(request, response);
			} else if("excluir".equals(exc)) {
				int codigo_setor = Integer.parseInt(request.getParameter("cd_setor"));
				Setor setor = new Setor();
				setor.setId(codigo_setor);
				remover(setor);
				this.conn.buscarTodosFuncionarios();
				RequestDispatcher rd = request.getRequestDispatcher("/cad_setor.jsp");
				rd.forward(request, response);
			}
			
			
			
		}else if("cad_funcionario".equals(paginaRequisicao)){
			
			String cad = request.getParameter("cadastrar");
			String exc = request.getParameter("excluir");
			
			if("cadastrar".equals(cad)) {
				int codigo_funcionario = Integer.parseInt(request.getParameter("cd_funcionario"));
				int codigo_setor = Integer.parseInt(request.getParameter("cd_setor"));
				String nome = request.getParameter("nome");
				
				Funcionario funcionario = new Funcionario();
				funcionario.setSetor(new Setor());
				funcionario.setId(codigo_funcionario);
				funcionario.setNome(nome);
				funcionario.getSetor().setId(codigo_setor);
				
				cadastrar(funcionario);
				this.conn.buscarTodosFuncionarios();
				RequestDispatcher rd = request.getRequestDispatcher("/cad_funcionario.jsp");
				rd.forward(request, response);
			} else if("excluir".equals(exc)) {
				int codigo_setor = Integer.parseInt(request.getParameter("cd_setor"));
				Funcionario funcionario = new Funcionario();
				funcionario.setId(codigo_setor);
				remover(funcionario);
				this.conn.buscarTodosFuncionarios();
				RequestDispatcher rd = request.getRequestDispatcher("/cad_funcionario.jsp");
				rd.forward(request, response);
			}
			
			
		}
		
	}

	private void remover(Funcionario funcionario) {
		conn.deletarFuncionario(funcionario);
	}

	private void cadastrar(Funcionario funcionario) {
		conn.inserirFuncionario(funcionario);
	}

	private void remover(Setor setor) {
		conn.deletarSetor(setor);
	}

	private void cadastrar(Setor setor) {
		conn.inserirSetor(setor);
	}

}
