package br.com.javaEstudo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Realiza a conexão com o banco de dados
 * Conexão mysql para o banco databasemysqltest
 * @author Mychel
 *
 */
public class ConnectionDataBaseMySQL {

	private static String banco = "jdbc:mysql://localhost:3306/databasemysqltest?autoReconnect=true";
	private static String user = "root";
	private static String password = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public ConnectionDataBaseMySQL() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(banco, user, password);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
		}
	}
	
	/**
	 * Retorna a conexão do banco de dados
	 * @return java.sql.Connection
	 */
	public static Connection getConnection() {
		return connection;
	}
}
