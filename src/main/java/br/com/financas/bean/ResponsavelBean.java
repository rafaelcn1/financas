package br.com.financas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.financas.dao.ResponsavelDAO;
import br.com.financas.model.Compra;
import br.com.financas.model.Responsavel;

@Named
@Stateless
public class ResponsavelBean {

	private Responsavel responsavel = new Responsavel();

	private List<Compra> comprasPorResponsavel = new ArrayList<Compra>();

	private double valorTotalEmComprasPeloResponsavel;

	private Integer responsavelId;

	@Inject
	private ResponsavelDAO responsavelDAO;

	public ResponsavelBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getListaResponsavel() {
		return responsavelDAO.listarTodos();
	}

	public double getValorTotalEmComprasPeloResponsavel() {
		double valor = 0;
		System.out.println(getResponsavel());
		List<Compra> todasCompras = getResponsavel().getCompras();
		for (Compra compra : todasCompras) {
			valor += compra.getValor();
		}
		valorTotalEmComprasPeloResponsavel = valor;
		return valorTotalEmComprasPeloResponsavel;
	}

	public void setValorTotalEmComprasPeloResponsavel(double valorTotalEmComprasPeloResponsavel) {
		this.valorTotalEmComprasPeloResponsavel = valorTotalEmComprasPeloResponsavel;
	}

	public List<Compra> getComprasPorResponsavel() {
		return comprasPorResponsavel;
	}

	public void setComprasPorResponsavel(List<Compra> comprasPorResponsavel) {
		this.comprasPorResponsavel = comprasPorResponsavel;
	}

	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}

	public void buscarCompraPorResponsavel() {
		Responsavel buscarPorId = responsavelDAO.buscarPorId(getResponsavelId());
		setResponsavel(buscarPorId);
		List<Compra> compras = getResponsavel().getCompras();
		setComprasPorResponsavel(compras);
	}

	public String cadastrarResponsavel() {
		responsavelDAO.salvar(getResponsavel());
		setResponsavel(new Responsavel());
		return "cadastrocompra?faces-redirect=true";
	}

	public void editarResponsavel(Integer id) {
		Responsavel buscarPorId = responsavelDAO.buscarPorId(id);
		setResponsavel(buscarPorId);
	}

	public void excluirResponsavel(Integer id) {
		Responsavel buscarPorId = responsavelDAO.buscarPorId(id);
		setResponsavel(buscarPorId);
		try {
			responsavelDAO.remover(getResponsavel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResponsavel(new Responsavel());
	}

}
