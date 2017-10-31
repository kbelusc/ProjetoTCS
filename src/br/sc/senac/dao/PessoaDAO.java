package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.sc.senac.entity.Pessoa;

public class PessoaDAO {
	
	private Connection conexao;
	
	public PessoaDAO(){
		conexao = ConnectionFactory.getInstance().obterConexao();
	}
	

	
	public int inserir(Pessoa p) {
		int idInserido = -1;
		String sql = "INSERT INTO Pessoa(email, tipoPessoa, documento, telefone, senha, nome) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setString(2, p.getEmail());
			ps.setInt(3, p.getTipoPessoa());
			ps.setString(4, p.getDocumento());
			ps.setString(5,p.getTelefone());
			ps.setString(6, p.getSenha());
			ps.setString(7, p.getNome());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Pessoa inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir Pessoa");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}
	
	public boolean alterar(Pessoa p) {
		
		boolean atualizadoSucesso = false;
		String sql = "UPDATE PESSOA SET email=?, tipoPessoa=?, documento=?, telefone=?, senha=?, nome=? where idPessoa=?";
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, p.getEmail());
			instrucaoSQL.setInt(2, p.getTipoPessoa());
			instrucaoSQL.setString(3, p.getDocumento());
			instrucaoSQL.setString(4, p.getTelefone());
			instrucaoSQL.setString(5, p.getSenha());
			instrucaoSQL.setString(6, p.getNome());
			
			int codigoRetorno = instrucaoSQL.executeUpdate();
			if(codigoRetorno == 1) {
				System.out.println("Pessoa Atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar Pessoa");	
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso; 
	}
	
	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM Pessoa WHERE id = ?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			
			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Pessoa removida com sucesso!");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover pessoa!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} 	
		return removidoSucesso;
	}
	
	public ArrayList<Pessoa> listar() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		
		String sql  = "SELECT * FROM Pessoa";
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();
		
			while(resultadoConsulta.next()) {
				Pessoa p = criarPessoaResultSet(resultadoConsulta);
				pessoas.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	private Pessoa criarPessoaResultSet(ResultSet resultadoConsulta) {
		Pessoa p = null;
		
		try {
			p = new Pessoa();
			p.setId(resultadoConsulta.getInt("id"));
			
					
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}


	private Connection getConexao() {
		return conexao;
	}
	

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	

}
