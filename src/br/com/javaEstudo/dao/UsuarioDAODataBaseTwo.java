package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.connection.ConnectionDataBaseTwo;
import br.com.javaEstudo.usuarios.Usuario;

public class UsuarioDAODataBaseTwo {

	private static Connection connectionDataBaseTwo;
	
	public UsuarioDAODataBaseTwo() {
		connectionDataBaseTwo = ConnectionDataBaseTwo.getConnection();
	}
	
	public List<Usuario> getUsuarios(String search) throws SQLException {
		List<Usuario> usuarios = new ArrayList();
		String sql = "select * from usuario where login like '%"+ search +"%'";
		
		PreparedStatement statement = connectionDataBaseTwo.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Usuario u = new Usuario();
			u.setId(resultSet.getLong("id"));
			u.setLogin(resultSet.getString("login"));
			u.setPassword(resultSet.getString("password"));
			usuarios.add(u);
		}
		
		return usuarios;
	}
}
