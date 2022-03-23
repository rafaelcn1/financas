package br.com.financas.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.model.Compra;
import br.com.financas.model.Credor;
import br.com.financas.model.Responsavel;
import br.com.financas.remote.CompraRemote;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompraDAO implements CompraRemote {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void salvar(Compra compra) {
		manager.persist(compra);

	}

	@Override
	public Compra buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return manager.find(Compra.class, id);
	}

	@Override
	public List<Compra> listarTodas() {
		// TODO Auto-generated method stub
		return manager.createQuery("select c from Compra c ORDER BY c.data", Compra.class).getResultList();
	}

	@Override
	public void remover(Compra compra) {
		manager.remove(compra);

	}

	@Override
	public void editar(Compra compra) {
		manager.merge(compra);
	}

}
