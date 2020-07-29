package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.connection.ConnectionDataBaseMySQL;
import br.com.javaEstudo.models.User;

public class DAOUserMySQL {

	private static Connection connection;
	
	public DAOUserMySQL() {
		connection = ConnectionDataBaseMySQL.getConnection();
	}
	
	public List<User> getUsuarios(String search) throws SQLException {
		List<User> usuarios = new ArrayList();
		String sql = "select * from usuario where login like '%"+ search +"%'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			User u = new User();
			u.setId(resultSet.getLong("id"));
			u.setLogin(resultSet.getString("login"));
			u.setPassword(resultSet.getString("password"));
			usuarios.add(u);
		}
		
		return usuarios;
	}
}
