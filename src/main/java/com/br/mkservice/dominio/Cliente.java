package com.br.mkservice.dominio;

import java.util.Date;

public class Cliente {

	private int id_cliente;
	private String nome;
	private String tel;
	private String end_cliente;
	private String email;
	private String textoFormatado;
	private Date dataAniversario;
	private String cpf;
	
	
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEnd_cliente() {
		return end_cliente;
	}
	public void setEnd_cliente(String end_cliente) {
		this.end_cliente = end_cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	public String getTextoFormatado() {
		return textoFormatado;
	}
	public void setTextoFormatado(String textoFormatado) {
		this.textoFormatado = textoFormatado;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
