package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.models.Event;
import br.com.javaEstudo.models.User;

public class DAOEvent {

	private static Connection connection;
	
	public DAOEvent() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<Event> getEvents() {
		List<Event> events = new ArrayList();

		try {
			String sql = "select * from event";

			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Event e = new Event();
				e.setId(resultSet.getLong("id"));
				e.setDescription(resultSet.getString("description"));
				e.setDate(resultSet.getString("date"));
				events.add(e);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return events;
	}
}
