package br.com.financas.remote;

import java.util.List;

import javax.ejb.Remote;

import br.com.financas.model.Compra;

@Remote
public interface CompraRemote {
	public void salvar(Compra compra);
	
	public void editar(Compra compra);

	public Compra buscarPorId(Integer id);
	
	public List<Compra> listarTodas();
	
	public void remover(Compra compra);

}
