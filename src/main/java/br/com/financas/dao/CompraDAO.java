package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.model.Compra;
import br.com.financas.remote.CompraRemote;

public class CompraDAO implements CompraRemote {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void salvar(Compra compra) {
		// TODO Auto-generated method stub
		if (compra.getId() != null) {
			manager.merge(compra);
		} else {
			manager.persist(compra);
		}

	}

	@Override
	public Compra buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> listarTodas() {
		// TODO Auto-generated method stub
		return manager.createQuery("select c from Compra c ORDER BY c.data", Compra.class).getResultList();
	}

}
