package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.model.Responsavel;
import br.com.financas.remote.ResponsavelRemote;

public class ResponsavelDAO implements ResponsavelRemote {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void salvar(Responsavel responsavel) {
		if (responsavel.getId() == null) {
			manager.persist(responsavel);
		} else {
			manager.merge(responsavel);
		}
	}

	@Override
	public List<Responsavel> listarTodos() {
		return manager.createQuery("select r from Responsavel r", Responsavel.class).getResultList();
	}

	@Override
	public Responsavel buscarPorId(Integer id) {
		return manager.find(Responsavel.class, id);
	}

	@Override
	public void remover(Responsavel responsavel) {
		manager.remove(responsavel);

	}

}
