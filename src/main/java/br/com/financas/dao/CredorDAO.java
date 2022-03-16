package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.model.Credor;
import br.com.financas.remote.CredorRemote;

public class CredorDAO implements CredorRemote {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void salvar(Credor credor) {
		// TODO Auto-generated method stub
		if (credor.getId() == null) {
			manager.persist(credor);
		} else {
			manager.merge(credor);
		}

	}

	@Override
	public List<Credor> listarTodos() {
		// TODO Auto-generated method stub
		String sql = "select a from Credor a ORDER BY a.nome"; // Ordenando pelo nome
		return manager.createQuery(sql, Credor.class).getResultList();
	}

	@Override
	public Credor buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return manager.find(Credor.class, id);
	}

	@Override
	public void excluir(Credor credor) {
		// TODO Auto-generated method stub
		manager.remove(credor);

	}

}
