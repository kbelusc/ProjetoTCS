package br.sc.senac.entity;

public class Endereco {
	
	private int id;
	private String estado;
	private String cidade;
	private String cep;
	private String bairro;
	private String rua;
	private int numero;
	private String complemento;
		
	public Endereco() {
		super();
	}

	public Endereco(int id, String estado, String cidade, String cep, String bairro, String rua, int numero,
			String complemento) {
		super();
		this.id = id;
		this.estado = estado;
		this.cidade = cidade;
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", estado=" + estado + ", cidade=" + cidade + ", cep=" + cep + ", bairro="
				+ bairro + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + "]";
	}


	

}
