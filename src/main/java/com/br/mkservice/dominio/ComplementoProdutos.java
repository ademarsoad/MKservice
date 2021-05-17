package com.br.mkservice.dominio;

public class ComplementoProdutos {
	public int id;
	public String cor;
	public int quantidade;
	public int produtos_id;
	public boolean complementos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getProdutos_id() {
		return produtos_id;
	}
	public void setProdutos_id(int produtos_id) {
		this.produtos_id = produtos_id;
	}
	public boolean isComplementos() {
		return complementos;
	}
	public void setComplementos(boolean complementos) {
		this.complementos = complementos;
	}
	
}
