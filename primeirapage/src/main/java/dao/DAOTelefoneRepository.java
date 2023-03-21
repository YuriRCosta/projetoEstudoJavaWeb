package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {

		connection = SingleConnectionBanco.getConnection();
		
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
			telefone.setUsuario_cadastro_id(daoUsuarioRepository.consultaUsuarioId(resultSet.getLong("usuario_cadastro_id")));
			telefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioId(resultSet.getLong("usuario_pai_id")));
		
			retorno.add(telefone);
			
		}
		
		return retorno;
		
	}
	
	public void gravaTelefone(ModelTelefone modelTelefone) throws SQLException {
		
		String sql = "insert into telefone (numero, usuario_pai_id, usuario_cadastro_id) values (?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, modelTelefone.getUsuario_cadastro_id().getId());

		statement.execute();
		connection.commit();
		
	}
	
	public void deleteFone (Long id) throws Exception{
		
		String sql = "delete from telefone where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, id);

		statement.executeUpdate();
		connection.commit();
	}
	
	public boolean existeFone(String fone, Long idUser) throws SQLException {
		
		String sql = "select count(1) > 0 as existe from telefone where usuario_pai_id = ? and numero = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, idUser);
		statement.setString(2, fone);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		return resultSet.getBoolean("existe");
		
	}
}
