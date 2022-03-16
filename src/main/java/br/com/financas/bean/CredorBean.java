package br.com.financas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.financas.dao.CredorDAO;
import br.com.financas.model.Compra;
import br.com.financas.model.Credor;

@Named
@Stateless
public class CredorBean {

	private Credor credor;

	private Integer credorId;

	private double valorTotalEmCompras;

	@Inject
	private CredorDAO credorDAO = new CredorDAO();

	private List<Compra> compras = new ArrayList<Compra>();

	public CredorBean() {
		super();
		credor = new Credor();
		// TODO Auto-generated constructor stub
	}

	public Credor getCredor() {
		return credor;
	}

	public void setCredor(Credor credor) {
		this.credor = credor;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Integer getCredorId() {
		return credorId;
	}

	public void setCredorId(Integer credorId) {
		this.credorId = credorId;
	}

	public double getValorTotalEmCompras() {
		double valor = 0;
		List<Compra> todasCompras = getCredor().getCompras();
		for (Compra compra : todasCompras) {
			valor += compra.getValor();
		}
		valorTotalEmCompras = valor;
		return valorTotalEmCompras;
	}

	public void setValorTotalEmCompras(double valorTotalEmCompras) {
		this.valorTotalEmCompras = valorTotalEmCompras;
	}

	public void buscarCompras() {
		Credor buscarCredorPorId = credorDAO.buscarPorId(getCredorId());
		setCredor(buscarCredorPorId);
		List<Compra> listaDeCompras = getCredor().getCompras();
		setCompras(listaDeCompras);

	}

	@Transactional
	public List<Credor> getListarCredores() {
		return credorDAO.listarTodos();

	}

	@Transactional
	public String cadastrarCredor() {
		credorDAO.salvar(getCredor());
		setCredor(new Credor());
		return "cadastrocompra?faces-redirect=true";
	}

	@Transactional
	public void editarCredor(Integer id) {
		Credor buscarPorId = credorDAO.buscarPorId(id);
		setCredor(buscarPorId);
		System.out.println(getCredor().toString());
	}

	@Transactional
	public void excluirCredor(Integer id) {
		Credor buscarPorId = credorDAO.buscarPorId(id);
		setCredor(buscarPorId);
		credorDAO.excluir(getCredor());
	}

}
