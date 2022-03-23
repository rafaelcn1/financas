package br.com.financas.bean;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class PaginasBean {

	public String irParaPaginaCadastroCompras() {
		return "cadastrocompra?faces-redirect=true";
	}

	public String irParaPaginaCadastroCredor() {
		return "cadastrocredor?faces-redirect=true";
	}

	public String irParaPaginaCadastroResponsavel() {
		return "cadastroresponsavel?faces-redirect=true";
	}

	public String irParaPaginaCobrancas() {
		return "cobrancas?faces-redirect=true";
	}

	public String irParaPaginaComprasPorCredor() {
		return "comprasporcredor?faces-redirect=true";
	}
	
	public String irParaPaginaComprasPorResponsavel() {
		return "comprasporesponsavel?faces-redirect=true";
	}

	public String irParaPaginaCompras() {
		return "compras?faces-redirect=true";
	}
	
	public String irParaPaginaCredores() {
		return "credores?faces-redirect=true";
	}
	
	public String irParaPaginaResponsaveis() {
		return "responsaveis?faces-redirect=true";
	}
}
