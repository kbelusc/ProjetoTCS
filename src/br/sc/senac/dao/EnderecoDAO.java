package br.sc.senac.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import br.sc.senac.entity.Endereco;

public class EnderecoDAO {
	
	private Connection conexao;
	
	public EnderecoDAO() {
		
		conexao = ConnectionFactory.getInstance().obterConexao();
	}
	
	public int inserir(Endereco e) {
		
		int idInserido = -1;
		String sql = "INSERT INTO Endereco(cidade, bairro, complemento, estado, cep, rua, numero) VALUES (?, ?, ?, ?, ?, ?, ?)";	
		
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, e.getCidade());
			ps.setString(2, e.getBairro());
			ps.setString(3, e.getComplemento());
			ps.setString(4, e.getEstado());
			ps.setString(5, e.getCep());
			ps.setString(6, e.getRua());
			ps.setInt(7, e.getNumero());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}
			if (idInserido > 0) {
				System.out.println("Endereço inserido com Sucesso!");				
			} else {
				System.out.println("Erro ao inserir carro!");
			}
			ps.close();		
			
		} catch(SQLException ex){
			ex.printStackTrace();			
		}		
		return idInserido;		
	}
	
	public boolean excluir(int id) {
		boolean removidoSucesso=false;
		
		String sql = "DELETE FROM Endereco WHERE id=?";
		
		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			
			int codigoRetorno = ps.executeUpdate();
			
			if (codigoRetorno == 1) {
				System.out.println("Endereco removido com sucesso!");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover carro!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();					
		}			
		return removidoSucesso;		
	}
	
	public boolean atualizar(Endereco e) {
		boolean atualizadoSucesso = false;
		
		String sql = "UPDATE Endereco SET cidade=?, bairro=?, complemento=?, estado=?, cep=?, rua=?, numero=? WHERE idEndereco=?";
		
		try {
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, e.getCidade());
			ps.setString(2, e.getBairro());
			ps.setString(3, e.getComplemento());
			ps.setString(4, e.getEstado());
			ps.setString(5, e.getCep());
			ps.setString(6, e.getRua());
			ps.setInt(7, e.getNumero());
			ps.setInt(8, e.getId());
			
			int codigoRetorno = ps.executeUpdate();
			if(codigoRetorno == 1) {
				System.out.println("Endereço alterado com Sucesso!");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar Endereço!");
			}			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return atualizadoSucesso;
		
	}
	
	public Endereco obterPorId(int idEndereco) {
		Endereco endereco = null;

		String sql = " SELECT * FROM Endereco WHERE ID=? ";

		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement(sql);
			ps.setInt(1, idEndereco);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			endereco = new Endereco();
			endereco.setBairro(rs.getString("bairro"));
			endereco.setCep(rs.getString("cep"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setComplemento(rs.getString("complemento"));
			endereco.setEstado(rs.getString("estado"));
			endereco.setId(rs.getInt("idEndereco"));
			endereco.setNumero(rs.getInt("numero"));
			endereco.setRua(rs.getString("rua"));
			
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return endereco;
	}
	
	public ArrayList<Endereco> listar() {
		ArrayList<Endereco> enderecos = new ArrayList<>();
		return enderecos;
	}

	private Connection getConexao() {
		
		return conexao;
	}
	
	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	
	
}
