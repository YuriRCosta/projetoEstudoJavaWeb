package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;

//@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
    
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
	
	
    public ServletTelefone() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				
				String idfone = request.getParameter("id");
				
				daoTelefoneRepository.deleteFone(Long.parseLong(idfone));
				
				String userpai = request.getParameter("userpai");

				ModelLogin login = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(userpai));
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(login.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("msg", "Telefone excluido");
				request.setAttribute("login", login);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}
			
			String iduser = request.getParameter("iduser");
			
			if( iduser != null && !iduser.isEmpty()) {
				
				ModelLogin login = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(login.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("login", login);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
			} else {
			
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("logins", logins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
	
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			if(!daoTelefoneRepository.existeFone(numero, Long.valueOf(usuario_pai_id))) {
				
				ModelTelefone modelTelefone = new ModelTelefone();
				
				modelTelefone.setNumero(numero);
				modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioId(Long.parseLong(usuario_pai_id)));
				modelTelefone.setUsuario_cadastro_id(super.getUserLogadoObject(request));
				
				daoTelefoneRepository.gravaTelefone(modelTelefone);

				
				request.setAttribute("msg", "Salvo com sucesso");
				
			} else {
				
				request.setAttribute("msg", "Telefone ja existe");

			}
			
			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(Long.parseLong(usuario_pai_id));
			ModelLogin login = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(usuario_pai_id));
			
			request.setAttribute("login", login);
			request.setAttribute("modelTelefones", modelTelefones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} 
	
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
