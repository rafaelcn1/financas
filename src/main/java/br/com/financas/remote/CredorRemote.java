package br.com.financas.remote;

import java.util.List;

import javax.ejb.Remote;

import br.com.financas.model.Credor;

@Remote
public interface CredorRemote {
	public void salvar(Credor credor);

	public List<Credor> listarTodos();
	
	public Credor buscarPorId(Integer id);
	
	public void excluir(Credor credor);

}
