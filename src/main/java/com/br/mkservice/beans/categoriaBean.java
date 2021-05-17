package com.br.mkservice.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.br.mkservice.conexao.Conexao;
import com.br.mkservice.dominio.Categoria;

@ManagedBean
@ViewScoped
public class categoriaBean {
	
	public List<Categoria> listaCategoria = new ArrayList<Categoria>();
	Categoria categoria;
	
	@PostConstruct
	public void pegaLista() {
		try {
			Connection conn;
			Conexao conecta = new Conexao();
			conn = conecta.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Select * from categoria";
			
			prst = conn.prepareStatement(sql);
			ResultSet rst = prst.executeQuery();
			listaCategoria.clear();
			while(rst.next()) {
				categoria = new Categoria();
				categoria.setId(rst.getInt("idCategoria"));
				categoria.setNome(rst.getString("titulo"));
				categoria.setDesc(rst.getString("desc"));
				categoria.getId();
				categoria.getNome();
				categoria.getDesc();
				
				listaCategoria.add(categoria);
			}
			
			conn.close();

		}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erro qualquer", e.getMessage()));
		}
	}
	
	
	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
}
