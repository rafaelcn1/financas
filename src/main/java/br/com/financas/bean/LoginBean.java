package br.com.financas.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;

import br.com.financas.dao.UsuarioDAO;
import br.com.financas.model.Usuario;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login, senha;

	private Usuario usuario;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UsuarioDAO usuarioDAO;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logon1() {
		// fazer busca no banco
		List<Usuario> listarTodosUsuariosCadastradoNoSistema = usuarioDAO.listarTodos();
		if (login.equals("root") && senha.equals("root")) {
			setUsuario(new Usuario("root", "root"));
			return "/pages/cadastrousuario.xhtml?faces-redirect=true";
		} else {
			for (Usuario usuario : listarTodosUsuariosCadastradoNoSistema) {
				if (usuario.getLogin().equalsIgnoreCase(login) && usuario.getSenha().equalsIgnoreCase(senha)) {
					setUsuario(usuario);
					return "/pages/compras.xhtml?faces-redirect=true";
				}

			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha invalida!", null));
		return null;

	}
	
	public void logon() {
		System.out.println(getLogin());
	}

	public void logout() {
		setUsuario(new Usuario());
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
