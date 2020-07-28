package br.com.javaEstudo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.javaEstudo.connection.ConnectionDataBase;
import br.com.javaEstudo.models.Imagem;

public class ImagemDAO {

private static Connection connection;
	
	public ImagemDAO() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<Imagem> getImagens(String search) throws SQLException {
		List<Imagem> imagens = new ArrayList();
		String sql = "select * from imagem";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Imagem i = new Imagem();
			i.setId(resultSet.getLong("id"));
			i.setImagem(resultSet.getBytes("imagem"));
			imagens.add(i);
		}
		
		return imagens;
	}
	
	public void addImagem(Imagem imagem) throws SQLException {
		String sql = "insert into imagem ( imagem, extensao ) values( ?, ? )";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setBytes(1, imagem.getImagem());
		statement.setString(2, imagem.getExtensao());
		statement.executeUpdate();
	}
}
