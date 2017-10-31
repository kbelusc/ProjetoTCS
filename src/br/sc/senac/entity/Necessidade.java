package br.sc.senac.entity;

import java.sql.Date;
import java.util.List;

public class Necessidade {
	
	private int id;
	private Date dtInicial;
	private Date DtFinal;
	private String statusNecessidade;
	private Endereco endereco;
	private Pessoa pessoa;
	private List<Item> itens;
	
	public Necessidade() {
		super();
	}

	public Necessidade(int id, Date dtInicial, Date dtFinal, String statusNecessidade, Endereco endereco, Pessoa pessoa,
			List<Item> itens) {
		super();
		this.id = id;
		this.dtInicial = dtInicial;
		DtFinal = dtFinal;
		this.statusNecessidade = statusNecessidade;
		this.endereco = endereco;
		this.pessoa = pessoa;
		this.itens = itens;
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
		return DtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		DtFinal = dtFinal;
	}

	public String getStatusNecessidade() {
		return statusNecessidade;
	}

	public void setStatusNecessidade(String statusNecessidade) {
		this.statusNecessidade = statusNecessidade;
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

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Necessidade [id=" + id + ", dtInicial=" + dtInicial + ", DtFinal=" + DtFinal + ", statusNecessidade="
				+ statusNecessidade + ", endereco=" + endereco + ", pessoa=" + pessoa + ", itens=" + itens + "]";
	}

	
}
