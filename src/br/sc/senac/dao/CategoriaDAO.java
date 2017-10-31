package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.entity.Categoria;



//private int id;
//private String nome;

public class CategoriaDAO {
	private Connection conexao;
	
	public Categoria obterPorId() {
		Categoria c = null;

		String sql = "SELECT * FROM CATEGORIA WHERE ID=?";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			int idCategoria = 0;
			stmt.setInt(1, idCategoria);
			stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c = this.criarCategoriaResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
			
	}
	
	
	public int inserir(Categoria c) {
		int idInserido = -1;
		String sql = "INSERT INTO Categoria(idCategoria, nomeCategoria) VALUES (?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());				
			ps.executeUpdate();
		

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Categoria inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir categoria");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}
	
	
	
	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM Categoria WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Categoria removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover categoria.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}
	
	
	public boolean atualizar(Categoria c) {
		boolean atualizadoSucesso = false;
		
		String sql = "UPDATE Categoria SET idCategoria=?, nomeCategoria=?";
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setInt(1, c.getId());
			instrucaoSQL.setString(2, c.getNome());           	

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Categoria atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar categoria");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}
	
	
	public ArrayList<Categoria> listar() {
		ArrayList<Categoria> categoria = new ArrayList<>();

		String sql = "SELECT * FROM Categoria";

		ArrayList<Categoria> categorias = null;
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova categoria para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				
				//VERIFICAR SE ESSA PARTE ESTÁ CERTA, ONDE CRIA UMA NOVA Categoria 
				Categoria c = criarCategoriaResultSet(resultadoConsulta);
				Categoria c1= null;				
				categorias.add(c1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}
		
	

	
	public ArrayList<Categoria> listarPorNome(String nomeCategoria) {
		ArrayList<Categoria> categorias = new ArrayList<>();

		String sql = "SELECT * FROM Categoria WHERE nomeCategoria=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, nomeCategoria);

			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova categoria  para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				Categoria c = criarCategoriaResultSet(resultadoConsulta);
				categorias.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	
	

	private Categoria criarCategoriaResultSet(ResultSet rs) {
		Categoria c = null;

		try {
			c= new Categoria();
			//int id;
			c.setId(rs.getInt("id"));			
			c.setNome(rs.getString("nome"));			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		
	}

	
	

	private Connection getConexao() {
		
		return conexao;
	}
	
	
}
