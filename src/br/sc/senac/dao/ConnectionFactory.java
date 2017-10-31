package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
	
private static ConnectionFactory instance;
	
	public static ConnectionFactory getInstance() {
		//Padrão Singleton
		if(instance == null) {
			instance = new ConnectionFactory();
		}
		
		return instance;
	}
	
	public Connection obterConexao() {
		//Configurações da conexão
		String nomeEsquema = "exemplobd";
		String enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema;
		String usuario = "root";
		String senha = "1234";
		String driverJDBC = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			System.out.println("Conexão aberta");
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o banco: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void fecharConexao(Connection con) {
		try {
			con.close();
			System.out.println("Conexão fechada");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	

}
