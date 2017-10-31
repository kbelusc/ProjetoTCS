package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.entity.Item;
import br.sc.senac.entity.Necessidade;

public class NecessidadeDAO {
	private Connection conexao;

	public Necessidade obterPorId() {
		Necessidade n = null;

		String sql = "SELECT * FROM NECESSIDADE WHERE ID=?";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			int idNecessidade = 0;
			stmt.setInt(1, idNecessidade);
			stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				n = this.criarNecessidadeResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n;
			
	}

	
	

	public int inserir(Necessidade n) {
		int idInserido = -1;
		String sql = "INSERT INTO NECESSIDADE(ID, DTINICIAL, DTFINAL, STATUSNECESSIDADE, ID_ENDERECO, ID_PESSOA) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setInt(1, n.getId());
			ps.setDate(2, n.getDtInicial());
			ps.setDate(3, n.getDtFinal());
			ps.setString(4, n.getStatusNecessidade());
			ps.setInt(5, n.getEndereco().getId());
			ps.setInt(6, n.getPessoa().getId());
	
			ps.executeUpdate();
		

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Necessidade inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir necessidade");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}
	
	
	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM NECESSIDADE WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Necessidade removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover necessidade.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}
	
	
	public boolean atualizar(Necessidade n) {
		boolean atualizadoSucesso = false;
		//VERIFICAR SE O PARAMETRO PASSADO PARA O SQL EST� CORRETO, PASSAOS PARAMETROS DE ACORDO COM A TABELA NO SCRIPT
		String sql = "UPDATE NECESSIDADE SET ID, DTINICIAL, DTFINAL, STATUSNECESSIDADE, ID_ENDERECO, ID_PESSOA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setInt(1, n.getId());
			instrucaoSQL.setDate(2, n.getDtInicial());			
			instrucaoSQL.setDate(3, n.getDtFinal());
			instrucaoSQL.setString(4, n.getStatusNecessidade());
			instrucaoSQL.setInt(5, n.getEndereco().getId());			
			instrucaoSQL.setInt(6, n.getPessoa().getId());
////////////instrucaoSQL.setArray(7, n.getItens().get(item));///////////PRECISO VERIFICAR COMO FAZ QUANDO � ARRAYLIST
			

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Necessidade atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar necessidade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}
	
	
	public ArrayList<Necessidade> listar() {
		ArrayList<Necessidade> necessidades = new ArrayList<>();

		String sql = "SELECT * FROM NECESSIDADE";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova necessidade para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				
				//VERIFICAR SE ESSA PARTE EST� CERTA, ONDE CRIA UMA NOVA NECESSIDADE 
				Necessidade d = criarNecessidadeResultSet(resultadoConsulta);
				Necessidade n = null;
				necessidades.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return necessidades;
	}
	
	

	

   /////PRECISO VERIFICAR COMO FAZ ESSE M�TODO, EST� COM MUITO ERRO
	private Necessidade criarNecessidadeResultSet(ResultSet resultadoConsulta) {
		Necessidade n = null;

		try {
			n = new Necessidade();
			n.setId(resultadoConsulta.getInt("id"));
			n.setDtInicial(resultadoConsulta.getDate("dtInicial"));
			n.setDtFinal(resultadoConsulta.getDate("dtFinal"));
			n.setStatusNecessidade(resultadoConsulta.getString("statusNecessidade"));
			//n.setEndereco(resultadoConsulta.get);
			
			ItemDAO itemDAO = new ItemDAO();
			//List<Item> lista = itemDAO.obterPorNecessidade(n.getId());
			//DTINICIAL, DTFINAL, STATUSNECESSIDADE, ID_ENDERECO, ID_PESSOA
			//int idPessoa = resultadoConsulta.getInt("id_pessoa");
			PessoaDAO pDAO = new PessoaDAO();
			//n.setPessoa(pDAO.obterPorId(resultadoConsulta.getInt("idPessoa")));
			EnderecoDAO endDAO = new EnderecoDAO();
			n.setEndereco(endDAO.obterPorId(resultadoConsulta.getInt("idEndereco")));
				    
			
			//n.setItens(lista);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
	private Connection getConexao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
