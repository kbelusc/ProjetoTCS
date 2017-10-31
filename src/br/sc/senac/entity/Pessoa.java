package br.sc.senac.entity;

public class Pessoa {
	
	private int id;
	private String email;
	private int tipoPessoa;
	private String documento;
	private String telefone;
	private String senha;
	private String nome;
	private Endereco endereco;
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(int id, String email, int tipoPessoa, String documento, String telefone, String senha, String nome,
			Endereco endereco) {
		super();
		this.id = id;
		this.email = email;
		this.tipoPessoa = tipoPessoa;
		this.documento = documento;
		this.telefone = telefone;
		this.senha = senha;
		this.nome = nome;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", email=" + email + ", tipoPessoa=" + tipoPessoa + ", documento=" + documento
				+ ", telefone=" + telefone + ", senha=" + senha + ", nome=" + nome + ", endereco=" + endereco + "]";
	}

	
	
}	