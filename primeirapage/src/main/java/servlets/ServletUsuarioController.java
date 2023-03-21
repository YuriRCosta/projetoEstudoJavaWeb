package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import util.ReportUtil;

import java.io.Console;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletInputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;
import dao.DAOUsuarioRepository;

@MultipartConfig
//@WebServlet( urlPatterns =  "/ServletUsuarioController")
public class ServletUsuarioController extends ServletGenericUtil {
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
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("logins", logins);
				request.setAttribute("msg", "Excluido com sucesso!");
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.excluirUser(idUser);

				response.getWriter().write("Excluido com sucesso!");
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPagina(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffset(nomeBusca, super.getUserLogado(request), pagina);
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.addHeader("totalPagina", "" + daoUsuarioRepository.consultaUsuarioListTotalPagina(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin login = daoUsuarioRepository.consultaUsuarioId(id, super.getUserLogado(request));
				
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("logins", logins);
				
				request.setAttribute("msg", "Usuario em edicao");
				request.setAttribute("login", login);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuarios carregados.");
				request.setAttribute("logins", logins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioListPaginada(this.getUserLogado(request), offset);
				
				request.setAttribute("logins", logins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
			
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request)));
					
				}  
				else {
					
					request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal));
					
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);

			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModelLogin> modelLogins = null;
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					modelLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request));
					
				}  
				else {
					
					modelLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request), dataInicial, dataFinal);

				}
				
				byte[] relatorio = new ReportUtil().geraRelatorioPDF(modelLogins, "resl-user-jsp", request.getServletContext());
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUsuarioRepository.montarGraficoMediaSalario(super.getUserLogado(request));
					
					ObjectMapper mapper = new ObjectMapper();
					
					String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
					
					response.getWriter().write(json);
					
				} else {
					
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUsuarioRepository.montarGraficoMediaSalario(super.getUserLogado(request), dataInicial, dataFinal);
					
					ObjectMapper mapper = new ObjectMapper();
					
					String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
					
					response.getWriter().write(json);
					
				}
				
			} else {
				List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("logins", logins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

			}
			
			
			
			
			
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
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendaMensal");
			rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.",	"").replaceAll("\\,", ".");

			ModelLogin login = new ModelLogin();
			
			login.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			login.setNome(nome);
			login.setEmail(email);
			login.setUsername(username);
			login.setPassword(password);
			login.setTelefone(telefone);
			login.setPerfil(perfil);
			login.setSexo(sexo);
			login.setCep(cep);
			login.setLogradouro(logradouro);
			login.setBairro(bairro);
			login.setLocalidade(localidade);
			login.setUf(uf);
			login.setNumero(numero);
			
			
			login.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
			login.setRendaMensal(Double.valueOf(rendaMensal));
				
			Part part = request.getPart("fileFoto");
			byte[] foto = IOUtils.toByteArray(part.getInputStream());
			if(foto.length > 0) {
				String imagemBase64 = "data:" + part.getContentType() + ";base64," + new Base64().encodeBase64String(foto);
				login.setFotouser(imagemBase64); 
				login.setExtensaofotouser(part.getContentType().split("\\/")[1]);
			}
		
			if(daoUsuarioRepository.validarLogin(login.getUsername()) && login.getId() == null) {
				msg = "Ja existe um usuario com o mesmo login";
			} else {
				if(login.isNovo()) {
					msg = "Cadastrado com sucesso!";
				} else {
					msg = "Atualizado com sucesso!";
				}
				login = daoUsuarioRepository.gravarUser(login, super.getUserLogado(request));
			}
				
			List<ModelLogin> logins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("logins", logins);
			request.setAttribute("msg", msg);
			request.setAttribute("login", login);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

			request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
		
	}

}
