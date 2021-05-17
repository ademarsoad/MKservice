package com.br.mkservice.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.br.mkservice.conexao.Conexao;
import com.br.mkservice.dominio.Cliente;
import com.br.mkservice.dominio.Endereco;

@ManagedBean
@ViewScoped
public class clientebean {
	
	Cliente cliente = new Cliente();
	Endereco endereco = new Endereco();
	public int retornaID;
	
	private List<Cliente> listaCliente = new ArrayList<Cliente>();
	
	public java.sql.Date converterDataUntilparaSql(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dataUntil = data;
		java.sql.Date dataSql = null;
		try {
			dataUntil = new java.sql.Date(dataUntil.getTime());
			dataSql = (java.sql.Date) dataUntil;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return dataSql;
	}

	
	public void cadastrarCliente() {
		
		try {
		Connection conn;
		Conexao conecta = new Conexao();
		
		conn = conecta.ligaBanco();
		PreparedStatement prst;
		
		String sql = "Insert into cliente(Nome, Tel, Email, Data_nasc) values (?, ?, ?, ?)";
		
		prst = conn.prepareStatement(sql);
		
		prst.setString(1, this.cliente.getNome());
		prst.setString(2, this.cliente.getTel());
		prst.setString(3, this.cliente.getEmail());
		prst.setDate(4, converterDataUntilparaSql(this.cliente.getDataAniversario()));
		
		prst.executeUpdate();
		
		cliente = new Cliente();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info	",
						"Inserido com sucesso"));
		
		
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
	}
	
	public int mostrarClientes() {
		System.out.println("Procurando ID do Cliente: ");
		
		try {
			Connection conn;
			Conexao conexao = new Conexao();
			conn = conexao.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Select id from cliente order by id desc limit 1";
			
			prst = conn.prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			
			while(rst.next()) {
				cliente.setId_cliente(rst.getInt("id"));
				cliente.getId_cliente();
				
				
				retornaID = cliente.getId_cliente();
				
			}
			conn.close();
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
		return retornaID;
	}
	
	public List<Cliente> pesquisaCliente(String nome) {
		try {
			Connection conn;
			Conexao conexao = new Conexao();
			conn = conexao.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Select * from cliente where nome like ?";
			
			prst = conn.prepareStatement(sql);
			prst.setString(1, '%' + nome + '%');
			ResultSet rst = prst.executeQuery();
			
			listaCliente.clear();
			while(rst.next()) {
				cliente = new Cliente();
				cliente.setId_cliente(rst.getInt("id"));
				cliente.setNome(rst.getString("nome"));
				cliente.setTel(rst.getString("tel"));
				cliente.setEmail(rst.getString("email"));
				cliente.setDataAniversario(rst.getDate("data_nasc"));
				cliente.getId_cliente();
				cliente.getNome();
				cliente.getTel();
				cliente.getEmail();
				cliente.getDataAniversario();
				
				listaCliente.add(cliente);
				
			}
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
		return listaCliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public int getRetornaID() {
		return retornaID;
	}


	public void setRetornaID(int retornaID) {
		this.retornaID = retornaID;
	}
	
}
