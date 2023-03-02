package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin login) throws Exception{
		
		String sql = "select * from model_login where upper(username) = upper(?) and upper(password) = upper(?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login.getUsername());
		statement.setString(2, login.getPassword());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}
		
		return false;
	}
	
}
