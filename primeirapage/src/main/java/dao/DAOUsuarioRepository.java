package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUser(ModelLogin objeto) throws SQLException {
		
		if(objeto.isNovo()) {

			String sql = "INSERT INTO public.model_login (username, password, nome, email, telefone) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getUsername());
			statement.setString(2, objeto.getPassword());
			statement.setString(3, objeto.getNome());
			statement.setString(4, objeto.getEmail());
			statement.setString(5, objeto.getTelefone());

			statement.execute();
			connection.commit();
			
		}else {
			String sql = "UPDATE public.model_login SET password=?, nome=?, email=?, telefone=? WHERE id = "+objeto.getId()+";";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(2, objeto.getPassword());
			statement.setString(3, objeto.getNome());
			statement.setString(4, objeto.getEmail());
			statement.setString(5, objeto.getTelefone());
			
			statement.executeUpdate();
			
			connection.commit();
			
		}
		
		
		
		return this.consultaUsuario(objeto.getUsername());
	}
	
	public ModelLogin consultaUsuario(String username) throws SQLException {
		
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
		String sql = "DELETE FROM public.model_login WHERE id = '"+idUser+"';";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		
		connection.commit();
	}
}
