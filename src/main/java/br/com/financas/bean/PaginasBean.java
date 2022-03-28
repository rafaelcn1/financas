package br.com.financas.bean;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named(value = "paginasBean")
@Stateless
public class PaginasBean {

	public String irParaPaginaCadastroCompras() {
		return "/pages/cadastrocompra?faces-redirect=true";
	}
	
	public String irParaPaginaCadastroUsuario() {
		return "/pages/cadastrousuario?faces-redirect=true";
	}

	public String irParaPaginaCadastroCredor() {
		return "/pages/cadastrocredor?faces-redirect=true";
	}

	public String irParaPaginaCadastroResponsavel() {
		return "/pages/cadastroresponsavel?faces-redirect=true";
	}

	public String irParaPaginaCobrancas() {
		return "/pages/cobrancas?faces-redirect=true";
	}

	public String irParaPaginaComprasPorCredor() {
		return "/pages/comprasporcredor?faces-redirect=true";
	}
	
	public String irParaPaginaComprasPorResponsavel() {
		return "/pages/comprasporesponsavel?faces-redirect=true";
	}

	public String irParaPaginaCompras() {
		return "/pages/compras?faces-redirect=true";
	}
	
	public String irParaPaginaCredores() {
		return "/pages/credores?faces-redirect=true";
	}
	
	public String irParaPaginaResponsaveis() {
		return "/pages/responsaveis?faces-redirect=true";
	}
}
