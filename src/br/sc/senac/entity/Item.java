package br.sc.senac.entity;

public class Item {
	
	private int id;
	private String descricao;
	private int quantidade;
	private String condicao;
	private Necessidade necessidade;
	private Doacao doacao;
	private Categoria categoria;
		
	public Item() {
		super();
	}

	public Item(int id, String descricao, int quantidade, String condicao, Necessidade necessidade, Doacao doacao,
			Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.condicao = condicao;
		this.necessidade = necessidade;
		this.doacao = doacao;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public Necessidade getNecessidade() {
		return necessidade;
	}

	public void setNecessidade(Necessidade necessidade) {
		this.necessidade = necessidade;
	}

	public Doacao getDoacao() {
		return doacao;
	}

	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", condicao=" + condicao
				+ ", necessidade=" + necessidade + ", doacao=" + doacao + ", categoria=" + categoria + "]";
	}


}	