package br.com.financas.remote;

import java.util.List;

import javax.ejb.Remote;

import br.com.financas.model.Usuario;

@Remote
public interface UsuarioRemote {

	public void salvar(Usuario usuario);

	public List<Usuario> listarTodos();

	public Usuario buscarPorId(Integer id);

	public void remover(Usuario usuario);

}
