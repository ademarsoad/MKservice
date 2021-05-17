package com.br.mkservice.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.br.mkservice.conexao.Conexao;
import com.br.mkservice.dominio.Produtos;

@ManagedBean
@ViewScoped
public class produtosBean {
	Produtos produtos = new Produtos();
	public int retornaIdProduto;
	public List<Produtos> listaProduto = new ArrayList<Produtos>();
	
	public void salvarProdutos() {
		try {
			Connection conn;
			Conexao conecta = new Conexao();
			
			conn = conecta.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Insert into produtos(titulo, descricao, peso, valor, quantidade, Categoria_idCategoria) values(?, ?, ?, ?, ?, ?)";
			
			prst = conn.prepareStatement(sql);
			
			prst.setString(1, this.produtos.getTitulo());
			prst.setString(2, this.produtos.getDesc());
			prst.setDouble(3, this.produtos.getPeso());
			prst.setDouble(4, this.produtos.getValor());
			prst.setInt(5, this.produtos.getQuantidade());
			prst.setInt(6, this.produtos.getCategoria());
		
			prst.executeUpdate();
			
			produtos = new Produtos();
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info	",
							"Inserido com sucesso"));
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int mostrarProduto() {
		
		try {
			Connection conn;
			Conexao conexao = new Conexao();
			conn = conexao.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Select id from produtos order by id desc limit 1";
			
			prst = conn.prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			
			while(rst.next()) {
				produtos.setId(rst.getInt("id"));
				produtos.getId();
				
				
				retornaIdProduto = produtos.getId();
				
			}
			conn.close();
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
		return retornaIdProduto;
	}
	
	public List<Produtos> pesquisaProduto(String titulo) {
		try {
			Connection conn;
			Conexao conexao = new Conexao();
			conn = conexao.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Select * from produtos where titulo like ?";
			
			prst = conn.prepareStatement(sql);
			prst.setString(1, '%' + titulo + '%');
			ResultSet rst = prst.executeQuery();
			
			listaProduto.clear();
			while(rst.next()) {
				produtos = new Produtos();
				produtos.setId(rst.getInt("id"));
				produtos.setTitulo(rst.getString("titulo"));
				produtos.setDesc(rst.getString("descricao"));
				produtos.setPeso(rst.getDouble("peso"));
				produtos.setValor(rst.getDouble("valor"));
				produtos.setQuantidade(rst.getInt("quantidade"));
				produtos.setCategoria(rst.getInt("categoria_Idcategoria"));
				produtos.getId();
				produtos.getTitulo();
				produtos.getDesc();
				produtos.getPeso();
				produtos.getValor();
				produtos.getQuantidade();
				produtos.getCategoria();
				
				listaProduto.add(produtos);
				
			}
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
		return listaProduto;
	}
		

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	public int getRetornaId() {
		return retornaIdProduto;
	}
	public void setRetornaId(int retornaId) {
		this.retornaIdProduto = retornaId;
	}
	public List<Produtos> getListaProduto() {
		return listaProduto;
	}
	public void setListaProduto(List<Produtos> listaProduto) {
		this.listaProduto = listaProduto;
	}
}
