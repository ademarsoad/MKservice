package com.br.mkservice.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.br.mkservice.conexao.Conexao;
import com.br.mkservice.dominio.ComplementoProdutos;
import com.br.mkservice.dominio.Produtos;

@ManagedBean
@ViewScoped
public class complementoProdutoBean {
	ComplementoProdutos complemento = new ComplementoProdutos();
	Produtos produtos = new Produtos();
	produtosBean produtosBean;
	private List<Integer> columns;
	
	public void checaComplemento(ValueChangeEvent event) {
		this.complemento.setComplementos((boolean) event.getNewValue());
		this.complemento.setCor(null);
		this.complemento.setQuantidade(0);
		FacesContext.getCurrentInstance().renderResponse();
	}
	@PostConstruct
    public void init() {
        columns = new ArrayList<Integer>();
        for (int i = 1; i < 4; i++) {
            columns.add(i);
        }
    }
	public void increment() {
        if (columns.size() < 20) {
            columns.add(columns.size() + 1);
        }
    }
 
    public void decrease() {
        if (columns.size() > 1) {
            columns.remove(columns.size() - 1);
        }
    }
	public void salvarComplemento() {
		
		if(complemento.complementos) {
			System.out.println("Produto tem complemento");
		}else {
			System.out.println("Produto sem complemento");
		}
		try {
			Connection conn;
			Conexao conecta = new Conexao();
			
			conn = conecta.ligaBanco();
			PreparedStatement prst;
			
			String sql = "Insert into complemento_produto(cores, quantidade, Produtos_Id) values (?, ?, ?)";
			
			prst = conn.prepareStatement(sql);
			
			prst.setString(1, this.complemento.cor);
			prst.setInt(2, this.complemento.quantidade);
			prst.setInt(3, this.produtos.getId());
			
			prst.executeUpdate();
			
			this.complemento = new ComplementoProdutos();
			
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro qualquer", e.getMessage()));
		}
	}
	
	public ComplementoProdutos getComplemento() {
		return complemento;
	}

	public void setComplemento(ComplementoProdutos complemento) {
		this.complemento = complemento;
	}
	public List<Integer> getColumns() {
		return columns;
	}
	public void setColumns(List<Integer> columns) {
		this.columns = columns;
	}
	
	
}
