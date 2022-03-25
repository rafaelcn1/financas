package br.com.financas.model;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login, senha;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

}
