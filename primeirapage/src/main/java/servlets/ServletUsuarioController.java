package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOUsuarioRepository;

public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServletUsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUser(idUser);
				request.setAttribute("msg", "Excluido com sucesso!");

			}
			
			request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String msg = "Operacao realizada com sucesso!";
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String telefone = request.getParameter("telefone");
			
			ModelLogin login = new ModelLogin();
			login.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			login.setNome(nome);
			login.setEmail(email);
			login.setUsername(username);
			login.setPassword(password);
			login.setTelefone(telefone);
			
			if(daoUsuarioRepository.validarLogin(login.getUsername())) {
				msg = "Ja existe um usuario com o mesmo login";
			} else {
				if(login.isNovo()) {
					msg = "Cadastrado com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}
				login = daoUsuarioRepository.gravarUser(login);
			}
			
			
			request.setAttribute("msg", msg);
			request.setAttribute("login", login);
			request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
		
	}

}
