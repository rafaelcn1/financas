package br.com.financas.remote;

import java.util.List;

import javax.ejb.Remote;

import br.com.financas.model.Responsavel;

@Remote
public interface ResponsavelRemote {

	public void salvar(Responsavel responsavel);

	public List<Responsavel> listarTodos();

	public Responsavel buscarPorId(Integer id);

	public void remover(Responsavel responsavel) throws Exception;

}
