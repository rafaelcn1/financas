package br.com.financas.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.financas.dao.UsuarioDAO;
import br.com.financas.model.Usuario;

@Named(value = "usuarioBean")
@Stateless
public class UsuarioBean {
	private Usuario usuario = new Usuario();

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UsuarioDAO usuarioDAO;


	public UsuarioBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<Usuario> listarTodosUsuariosCadastrados = listarTodosUsuariosCadastrados();
		for (Usuario usuario : listarTodosUsuariosCadastrados) {
			if (usuario.getLogin().equalsIgnoreCase(getUsuario().getLogin())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Usuário " + getUsuario().getLogin() + " já está cadastrado!", null));
				return null;
			} 
		}
		usuarioDAO.salvar(getUsuario());
		
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Usuário " + getUsuario().getLogin() + " cadastrado com sucesso!", null));
		setUsuario(new Usuario());
		return "/pages/cadastrocompra.xhtml?faces-redirect=true";

	}
	
	public List<Usuario> listarTodosUsuariosCadastrados(){
		return usuarioDAO.listarTodos();
	}

}
