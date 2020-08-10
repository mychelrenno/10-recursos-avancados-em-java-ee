package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.models.Projeto;
import br.com.javaEstudo.models.Serie;

public class DAOGanttView {

	private static Connection connection;
	
	public DAOGanttView() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<Projeto> getProjetos() {
		List<Projeto> projetos = new ArrayList();

		try {
			String sqlProjeto = "select * from projeto";
			PreparedStatement statementProjeto = connection.prepareStatement(sqlProjeto);
			ResultSet resultSetProjeto = statementProjeto.executeQuery();

			while (resultSetProjeto.next()) {
				Projeto p = new Projeto();
				p.setId(resultSetProjeto.getLong("id"));
				p.setName(resultSetProjeto.getString("name"));
				
				String sqlSerie = "select * from serie where id_projeto = " + p.getId();
				PreparedStatement statementSerie = connection.prepareStatement(sqlSerie);
				ResultSet resultSetSerie = statementSerie.executeQuery();
				
				List<Serie> series = new ArrayList<>();
				while (resultSetSerie.next()) {
					Serie s = new Serie();
					s.setId(resultSetSerie.getLong("id"));
					s.setName(resultSetSerie.getString("name"));
					s.setStart(resultSetSerie.getString("start"));
					s.setEnd(resultSetSerie.getString("end"));
					s.setColor(resultSetSerie.getString("color"));
					s.setIdProjeto(resultSetSerie.getLong("id_projeto"));
					series.add(s);
				}
				
				p.setSeries(series);
				projetos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projetos;
	}
	
	public void atualizar(Serie s) throws SQLException {
		
		String sqlUpdate = "update serie set id = " + s.getId() + ", name = '" + s.getName() + "', start = '" + s.getStart() +
				"', \"end\" = '" + s.getEnd() + "', id_projeto = " + s.getIdProjeto() + ", color = '" + s.getColor() + "' where id = " + s.getId();
		
		System.out.println(sqlUpdate);
		
		PreparedStatement statementProjeto = connection.prepareStatement(sqlUpdate);
		statementProjeto.executeUpdate();
		
	}
}
