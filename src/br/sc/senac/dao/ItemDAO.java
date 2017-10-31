
package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.entity.Doacao;
import br.sc.senac.entity.Item;
import br.sc.senac.entity.Necessidade;

public class ItemDAO {
	
	private Connection conexao;

	public ItemDAO() {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}

	
	//private int id;
	//private String tipo;
	//private int quantidade;
	//private String condicao;
	//private Necessidade necessidade;
	//private Doacao doacao;
	
		
	public int inserir(Item i) {
		int idInserido = -1;
		String sql = "INSERT INTO Item( tipo, quantidade, condicao, idNecessidade, idDoacao) VALUES (?, ?, ?, ?, ?, ?)";
		try { 
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenchendo a consulta com os atributos do objeto
			
			//ps.setString(1,i.getTipo());
			ps.setInt(2,i.getQuantidade());
			ps.setString(3, i.getCondicao());
			ps.setInt(4, i.getNecessidade().getId());
			ps.setInt(5, i.getDoacao().getId());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Item inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir item");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	
	}

	
	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM ITEM WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Item removido com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover item.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}
	
	public boolean atualizar(Item i) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE ITEM SET TIPO=?, QUANTIDADE=?, CONDICAO=?, IDNECESSIDADE, IDDOACAO,  WHERE ID=?";
		

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			//instrucaoSQL.setString(1,i.getTipo());
			instrucaoSQL.setInt(2,i.getQuantidade());
			instrucaoSQL.setString(3, i.getCondicao());
			instrucaoSQL.setInt(4, i.getNecessidade().getId());
			instrucaoSQL.setInt(5, i.getDoacao().getId());
			
			

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("�TEM atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar �tem.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}
	
	
	public Item obterPorId(int idItem) {
		Item i = null;

		String sql = "SELECT * FROM ITEM WHERE ID=?";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i = this.criarItemResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}
	

	
	
	
	
	public ArrayList<Item> listar() {
		ArrayList<Item> itens = new ArrayList<>();

		String sql = "SELECT * FROM ITEM";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				
				Item i = criarItemResultSet(resultadoConsulta);
				itens.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}
	
	public ArrayList<Item> listarPorDoacao(int idDoacao) {
		ArrayList<Item> itens = new ArrayList<>();

		String sql = "SELECT * FROM ITEM I";
		sql+= "WHERE I.IDDOACAO = ?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setInt(1, idDoacao);
			
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				
				Item i = criarItemResultSet(resultadoConsulta);
				itens.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}
	
	
	
	//int idMontadora = resultadoConsulta.getInt("id_montadora");
	//MontadoraDAO montadoraDAO = new MontadoraDAO();
	//Montadora m = montadoraDAO.obterPorId(idMontadora);
	
	
	private Item criarItemResultSet(ResultSet resultadoConsulta) {
		Item i= null;
		try {
			i = new Item();
			i.setId(resultadoConsulta.getInt("idItem"));
			int idNecessidade = resultadoConsulta.getInt("idNecessidade");
			NecessidadeDAO necessidadeDAO = new NecessidadeDAO();
			Necessidade n= necessidadeDAO.obterPorId();
			
			i.setNecessidade(n);	
			//i.setTipo(resultadoConsulta.getString("tipo"));
			i.setQuantidade(resultadoConsulta.getInt("quantidade"));
			i.setCondicao(resultadoConsulta.getString("condicao"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
       //ps.setInt(4, i.getNecessidade().getId());
	   //ps.setInt(5, i.getDoacao().getId());
	
	public ArrayList<Item> listarPorTipo(String tipoItem) {
		ArrayList<Item> itens = new ArrayList<>();

		String sql = "SELECT * FROM ITEM WHERE TIPO=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, tipoItem);

			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Criando um novo item para cada item retornado no resultSet
				// Usando como chave o nome da coluna na tabela
				Item i = criarItemResultSet(resultadoConsulta);
				itens.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}
	
	
	private Connection getConexao() {
		
		return null;
	}
	
	
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}


	public List<Item> obterPorDoacao(int idDoacao) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
