package br.sc.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.entity.Doacao;
import br.sc.senac.entity.Item;
import br.sc.senac.entity.Pessoa;

public class DoacaoDAO {
	
	private Connection conexao;

	public DoacaoDAO() {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}

	public int inserir(Doacao d) {
		int idInserido = -1;
		
		String sql = "INSERT INTO Doacao(dtInicial, dtFinal, statusDoacao, idEndereco, idPessoa) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setDate(1, d.getDtInicial());
			ps.setDate(2, d.getDtFinal());
			ps.setString(3, d.getStatusDoacao());
			ps.setInt(4, d.getEndereco().getId());
			ps.setInt(5, d.getPessoa().getId());
			ps.executeUpdate();			

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Doacao inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir Doacao");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}
	
	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM Doacao WHERE idDoacao = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Doacao removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover Doacao.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}
	
	public boolean atualizar(Doacao d) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE Doacao SET dtInicial=?, dtFinal=?, statusDoacao=?, idEndereco=?, idPessoa=?";		

		try {			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setDate(1, d.getDtInicial());
			ps.setDate(2, d.getDtFinal());
			ps.setString(3, d.getStatusDoacao());
			ps.setInt(4, d.getEndereco().getId());
			ps.setInt(5, d.getPessoa().getId());
			
			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Doação atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar Doação.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}
	
//	public Doacao obterPorId(int idDoacao) {
//		Doacao doacao = null;
//		String sql = "SELECT * FROM Doacao WHERE idDoacao=?";
//
//		PreparedStatement ps;
//		try {
//			ps = conexao.prepareStatement(sql);
//			ps.setInt(1, idDoacao);
//			
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				doacao = new Doacao();
//				doacao.setId(rs.getInt("idDoacao"));
//				
//				doacao.setDtInicial(rs.getDate("dtInicial"));
//				doacao.setDtFinal(rs.getDate("dtFinal"));
//				doacao.setStatusDoacao(rs.getString("statusDoacao"));
//				
//				PessoaDAO pDAO = new PessoaDAO();
//				int idPessoa  = rs.getInt("idPessoa");				
//				Pessoa pessoa = pDAO.obterPorId(idPessoa);
//				doacao.setPessoa(pessoa);
//				
//				EnderecoDAO eDAO = new EnderecoDAO();
//				int idEndereco = rs.getInt("idEndereco");				
//				Endereco endereco = eDAO.obterPorId(idEndereco);
//				doacao.setEndereco(endereco);
//				
//			}
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return doacao;
//	}
	
	public ArrayList<Doacao> listar() {
		ArrayList<Doacao> Doacoes = new ArrayList<>();
		
		String sql = "SELECT * FROM Doacao";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
//			-----COMPLETAR AQUI-------
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return Doacoes;		
	}
	
}