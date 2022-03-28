package br.com.financas.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.model.Usuario;
import br.com.financas.remote.UsuarioRemote;

public class UsuarioDAO implements UsuarioRemote,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		List<Usuario> listaDeTodosUsuarios = manager.createQuery("from Usuario", Usuario.class).getResultList();
		return listaDeTodosUsuarios;
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Usuario usuario) {
		// TODO Auto-generated method stub

	}

}
