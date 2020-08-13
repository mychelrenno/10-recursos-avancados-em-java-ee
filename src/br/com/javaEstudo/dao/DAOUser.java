package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.models.User;

public class DAOUser {

	private static Connection connection;
	
	public DAOUser() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<User> getUsers(String search) {
		List<User> usuarios = new ArrayList();
		try {
			String sql = "select * from usuario";
			
			if (search != null && !search.isEmpty()) {
				sql += "where login like '%" + search + "%'";
			}

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User u = new User();
				u.setId(resultSet.getLong("id"));
				u.setLogin(resultSet.getString("login"));
				u.setPassword(resultSet.getString("password"));
				usuarios.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
}
