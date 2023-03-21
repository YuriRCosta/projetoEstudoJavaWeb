package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import beandto.BeanDtoGraficoSalarioUser;
import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUser(ModelLogin objeto, Long userLogado) throws SQLException {
		
		if(objeto.isNovo()) {

			String sql = "INSERT INTO public.model_login (username, password, nome, email, telefone, usuario_id, perfil, sexo, fotouser, extensaofotouser, cep, logradouro, bairro, localidade, uf, numero, datanascimento, rendamensal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getUsername());
			statement.setString(2, objeto.getPassword());
			statement.setString(3, objeto.getNome());
			statement.setString(4, objeto.getEmail());
			statement.setString(5, objeto.getTelefone());
			statement.setLong(6, userLogado);
			statement.setString(7, objeto.getPerfil());
			statement.setString(8, objeto.getSexo());
			statement.setString(9, objeto.getFotouser());
			statement.setString(10, objeto.getExtensaofotouser());
			statement.setString(11, objeto.getCep());
			statement.setString(12, objeto.getLogradouro());
			statement.setString(13, objeto.getBairro());
			statement.setString(14, objeto.getLocalidade());
			statement.setString(15, objeto.getUf());
			statement.setString(16, objeto.getNumero());
			statement.setDate(17, objeto.getDataNascimento());
			statement.setDouble(18, objeto.getRendaMensal());
			
			statement.execute();
			connection.commit();
			
		}else {
			String sql = "UPDATE model_login SET password=?, nome=?, email=?, telefone=?, perfil=?, sexo=?, cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?, datanascimento=?, rendamensal=? WHERE id = "+objeto.getId()+";";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, objeto.getPassword());
			statement.setString(2, objeto.getNome());
			statement.setString(3, objeto.getEmail());
			statement.setString(4, objeto.getTelefone());
			statement.setString(5, objeto.getPerfil());
			statement.setString(6, objeto.getSexo());
			statement.setString(7, objeto.getCep());
			statement.setString(8, objeto.getLogradouro());
			statement.setString(9, objeto.getBairro());
			statement.setString(10, objeto.getLocalidade());
			statement.setString(11, objeto.getUf());
			statement.setString(12, objeto.getNumero());
			statement.setDate(13, objeto.getDataNascimento());
			statement.setDouble(14, objeto.getRendaMensal());
			
			statement.executeUpdate();
			
			connection.commit();
			
			if(objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "UPDATE model_login SET fotouser=?, extensaofotouser=? WHERE id = "+objeto.getId()+";";
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, objeto.getFotouser());
				statement.setString(2, objeto.getExtensaofotouser());
				
				statement.executeUpdate();
				
				connection.commit();
			}
			
		}
		
		
		
		return this.consultaUsuario(objeto.getUsername(), userLogado);
	}
	
	public int totalPagina(Long userLogado) throws Exception{
		
		String sql = "SELECT count(1) as total from model_login where usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		Double cadastros = resultSet.getDouble("total");
		
		Double porPagina = 5.0;
				
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if(resto>0) {
			pagina++;
		}
		
		return pagina.intValue();
	}
	
	public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where useradmin is false and usuario_id = " + userLogado + " order by nome offset "+offset+" limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setPerfil(resultSet.getString("perfil"));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where useradmin is false and usuario_id = " + userLogado + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setPerfil(resultSet.getString("perfil"));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public BeanDtoGraficoSalarioUser montarGraficoMediaSalario(Long userLogado) throws Exception {
		
		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id = ? group by perfil";
				
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, userLogado);
		
		ResultSet resultSet = statement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
		
		while (resultSet.next()) {
			
			Double medial_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");

			perfils.add(perfil);
			salarios.add(medial_salarial);
			
		}
		
		beanDtoGraficoSalarioUser.setPerfils(perfils);
		beanDtoGraficoSalarioUser.setSalarios(salarios);
		
		return beanDtoGraficoSalarioUser;
		
	}
	
	public BeanDtoGraficoSalarioUser montarGraficoMediaSalario(Long userLogado, String dataInicial, String dataFinal) throws Exception {
		
		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id = ? and datanascimento >= ? and datanascimento <= ? group by perfil";
				
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, userLogado);
		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		
		ResultSet resultSet = statement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
		
		while (resultSet.next()) {
			
			Double medial_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");

			perfils.add(perfil);
			salarios.add(medial_salarial);
			
		}
		
		beanDtoGraficoSalarioUser.setPerfils(perfils);
		beanDtoGraficoSalarioUser.setSalarios(salarios);
		
		return beanDtoGraficoSalarioUser;
		
	}
	
	public List<ModelLogin> consultaUsuarioListRel(Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));
			
			login.setTelefones(this.listFone(login.getId()));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public List<ModelLogin> consultaUsuarioListRel(Long userLogado, String dataInicial, String dataFinal) throws SQLException, ParseException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where useradmin is false and usuario_id = " + userLogado + " and datanascimento >= ? and datanascimento <= ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));

		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));
			
			login.setTelefones(this.listFone(login.getId()));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public int consultaUsuarioListTotalPagina(String nome, Long userLogado) throws SQLException {
		
		String sql = "SELECT count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		Double cadastros = resultSet.getDouble("total");
		
		Double porPagina = 5.0;
				
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if(resto>0) {
			pagina++;
		}
		
		return pagina.intValue();
 		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public List<ModelLogin> consultaUsuarioListOffset(String nome, Long userLogado, String offset) throws SQLException {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset "+offset+" limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin login = new ModelLogin();
			
			login.setEmail(resultSet.getString("email"));
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setNome(resultSet.getString("nome"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			
			retorno.add(login);
		}
		
		return retorno;
 		
	}
	
	public ModelLogin consultaUsuarioLogado(String username) throws SQLException {
		
		ModelLogin login = new ModelLogin();
		
		String sql = "SELECT * from model_login where upper(username) = upper('"+username+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setPassword(resultSet.getString("password"));
			login.setEmail(resultSet.getString("email"));
			login.setNome(resultSet.getString("nome"));
			login.setTelefone(resultSet.getString("telefone"));
			login.setUseradmin(resultSet.getBoolean("useradmin"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			login.setFotouser(resultSet.getString("fotouser"));
			login.setCep(resultSet.getString("cep"));
			login.setLogradouro(resultSet.getString("logradouro"));
			login.setBairro(resultSet.getString("bairro"));
			login.setLocalidade(resultSet.getString("localidade"));
			login.setUf(resultSet.getString("uf"));
			login.setNumero(resultSet.getString("numero"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));

		}
		
		return login;
		
	}
	
	public ModelLogin consultaUsuario(String username) throws SQLException {
		
		ModelLogin login = new ModelLogin();
		
		String sql = "SELECT * from model_login where upper(username) = upper('"+username+"') and useradmin is false";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setPassword(resultSet.getString("password"));
			login.setEmail(resultSet.getString("email"));
			login.setNome(resultSet.getString("nome"));
			login.setTelefone(resultSet.getString("telefone"));
			login.setUseradmin(resultSet.getBoolean("useradmin"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			login.setCep(resultSet.getString("cep"));
			login.setLogradouro(resultSet.getString("logradouro"));
			login.setBairro(resultSet.getString("bairro"));
			login.setLocalidade(resultSet.getString("localidade"));
			login.setUf(resultSet.getString("uf"));
			login.setNumero(resultSet.getString("numero"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));

		}
		
		return login;
		
	}
	
	public ModelLogin consultaUsuario(String username, Long userLogado) throws SQLException {
		
		ModelLogin login = new ModelLogin();
		
		String sql = "SELECT * from model_login where upper(username) = upper('"+username+"') and useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setPassword(resultSet.getString("password"));
			login.setEmail(resultSet.getString("email"));
			login.setNome(resultSet.getString("nome"));
			login.setTelefone(resultSet.getString("telefone"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			login.setCep(resultSet.getString("cep"));
			login.setLogradouro(resultSet.getString("logradouro"));
			login.setBairro(resultSet.getString("bairro"));
			login.setLocalidade(resultSet.getString("localidade"));
			login.setUf(resultSet.getString("uf"));
			login.setNumero(resultSet.getString("numero"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));

		}
		
		return login;
		
	}
	
	public ModelLogin consultaUsuarioId(Long id) throws SQLException {
		
		ModelLogin login = new ModelLogin();
		
		String sql = "SELECT * from model_login where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);

		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setPassword(resultSet.getString("password"));
			login.setEmail(resultSet.getString("email"));
			login.setNome(resultSet.getString("nome"));
			login.setTelefone(resultSet.getString("telefone"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			login.setCep(resultSet.getString("cep"));
			login.setLogradouro(resultSet.getString("logradouro"));
			login.setBairro(resultSet.getString("bairro"));
			login.setLocalidade(resultSet.getString("localidade"));
			login.setUf(resultSet.getString("uf"));
			login.setNumero(resultSet.getString("numero"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));

		}
		
		return login;
		
	}
	
	public ModelLogin consultaUsuarioId(String id, Long userLogado) throws SQLException {
		
		ModelLogin login = new ModelLogin();
		
		String sql = "SELECT * from model_login where id = ? and useradmin is false and usuario_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			login.setId(resultSet.getLong("id"));
			login.setUsername(resultSet.getString("username"));
			login.setPassword(resultSet.getString("password"));
			login.setEmail(resultSet.getString("email"));
			login.setNome(resultSet.getString("nome"));
			login.setTelefone(resultSet.getString("telefone"));
			login.setPerfil(resultSet.getString("perfil"));
			login.setSexo(resultSet.getString("sexo"));
			login.setCep(resultSet.getString("cep"));
			login.setLogradouro(resultSet.getString("logradouro"));
			login.setBairro(resultSet.getString("bairro"));
			login.setLocalidade(resultSet.getString("localidade"));
			login.setUf(resultSet.getString("uf"));
			login.setNumero(resultSet.getString("numero"));
			login.setDataNascimento(resultSet.getDate("datanascimento"));
			login.setRendaMensal(resultSet.getDouble("rendamensal"));

		}
		
		return login;
		
	}
	
	public boolean validarLogin(String username) throws Exception{
		String sql = "select count(1) > 0 as existe from model_login where upper(username) = upper('"+username+"')";
	
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		return resultSet.getBoolean("existe");
		
	}
	
	public void excluirUser(String idUser) throws SQLException {
		String sql = "DELETE FROM public.model_login WHERE id = '"+idUser+"' and useradmin is false;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		
		connection.commit();
	}
	
	public List<ModelTelefone> listFone(Long idUserPai) throws SQLException{
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, idUserPai);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelTelefone telefone = new ModelTelefone();
			
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setUsuario_cadastro_id(this.consultaUsuarioId(resultSet.getLong("usuario_cadastro_id")));
			telefone.setUsuario_pai_id(this.consultaUsuarioId(resultSet.getLong("usuario_pai_id")));
		
			retorno.add(telefone);
			
		}
		
		return retorno;
		
	}
	
}
