package br.com.javaEstudo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Realiza a conexão com o banco de dados
 * Conexão postgresql para o banco databasetesttwo
 * @author Mychel
 *
 */
public class ConnectionDataBaseTwo {

	private static String banco = "jdbc:postgresql://localhost:5432/databasetesttwo?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public ConnectionDataBaseTwo() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
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
