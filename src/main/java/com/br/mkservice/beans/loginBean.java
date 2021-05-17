package com.br.mkservice.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.br.mkservice.dominio.Login;

@ManagedBean
@ApplicationScoped
public class loginBean {
	public Login login = new Login();
	public List<Login> logins = new ArrayList<Login>();
	
	public String logar() {
		this.logins.add(login);
		if(this.login.usuario.equals("Ademar") && this.login.senha.equals("ademar")) {
			
			System.out.println("Login e Senha corretos");
			return "/principal.xhtml";
		}else {
			String msg = "Login ou senha está incorreto!!!";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		return "";
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getLogins() {
		return logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}
	
	
}
