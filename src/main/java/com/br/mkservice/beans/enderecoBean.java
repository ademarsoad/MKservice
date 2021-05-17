package com.br.mkservice.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.br.mkservice.buscacep.WebServiceCep;
import com.br.mkservice.conexao.Conexao;
import com.br.mkservice.dominio.Cliente;
import com.br.mkservice.dominio.Endereco;

@ManagedBean
@ViewScoped
public class enderecoBean {
	
	Endereco endereco = new Endereco();
	Cliente cliente = new Cliente();
	clientebean clienteBean = new clientebean();
	
	public void buscaCep() {
		WebServiceCep webServiceCep = WebServiceCep.searchCep(this.endereco.getCep());
		
		 if(webServiceCep.wasSuccessful()) {
			 System.out.println("Cep: " + webServiceCep.getCep());
	         System.out.println("Logradouro: " + webServiceCep.getLogradouro());
	         System.out.println("Bairro: " + webServiceCep.getBairro());
	         System.out.println("Cidade: "
	                 + webServiceCep.getCidade() + "/" + webServiceCep.getUf());
	         
	         this.endereco.setRua(webServiceCep.getLogradouro());
	         this.endereco.setBairro(webServiceCep.getBairro());
	         
		 }
		 else {
			 System.out.println("Não foi encontrado o cep informado " + this.endereco.getCep());
		 }
	}
	
	public void salvarEndereco() {
			clienteBean.mostrarClientes();
			System.out.println("Id do cliente para endereço: " + clienteBean.retornaID);
		try {
				Connection conn;
				Conexao conecta = new Conexao();
				
				conn = conecta.ligaBanco();
				PreparedStatement prst;
				
				String sql = "Insert into endereco(rua, bairro, cep, complemento, cliente_id) "
						+ " values(?, ?, ?, ?, ?) ";
				
				prst = conn.prepareStatement(sql);
				
				prst.setString(1, this.endereco.getRua());
				prst.setString(2, this.endereco.getBairro());
				prst.setString(3, this.endereco.getCep());
				prst.setString(4, this.endereco.getComplemento());
				prst.setInt(5, this.clienteBean.retornaID);
				
				prst.executeUpdate();
				
				this.endereco = new Endereco();
			}catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Erro qualquer", e.getMessage()));
			}
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
