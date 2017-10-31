package br.sc.senac.entity;

import java.sql.Date;
import java.util.List;

public class Doacao {
	
	private int id;
	private Date dtInicial;
	private Date dtFinal;
	private String statusDoacao;
	private Endereco endereco;
	private Pessoa pessoa;
	
	
	public Doacao() {
		super();
	}

	public Doacao(int id, Date dtInicial, Date dtFinal, String statusDoacao, Endereco endereco, Pessoa pessoa
			) {
		super();
		this.id = id;
		this.dtInicial = dtInicial;
		this.dtFinal = dtFinal;
		this.statusDoacao = statusDoacao;
		this.endereco = endereco;
		this.pessoa = pessoa;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getStatusDoacao() {
		return statusDoacao;
	}

	public void setStatusDoacao(String statusDoacao) {
		this.statusDoacao = statusDoacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Doacao [id=" + id + ", dtInicial=" + dtInicial + ", dtFinal=" + dtFinal + ", statusDoacao="
				+ statusDoacao + ", endereco=" + endereco + ", pessoa=" + pessoa + "]";
	}

	
	
}
