package br.com.financas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.financas.dao.CredorDAO;
import br.com.financas.model.Compra;
import br.com.financas.model.Credor;

@Named(value = "cobrancaBean")
@Stateless
public class CobrancaBean {

	private Integer mes, credorId;

	private Credor credor = new Credor();

	private List<Credor> todosOsCredores = new ArrayList<Credor>();

	private List<Compra> listaDeComprasPorCredorNoMes = new ArrayList<Compra>();

	private double valorDaCobrancaMes;
	
	private double valorDaCobrancaEmAbertoNoMes;
	private double valorDaCobrancaPagoNoMes;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private CredorDAO credorDAO;

	public CobrancaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getCredorId() {
		return credorId;
	}

	public void setCredorId(Integer credorId) {
		this.credorId = credorId;
	}

	public Credor getCredor() {
		return credor;
	}

	public void setCredor(Credor credor) {
		this.credor = credor;
	}

	public List<Credor> getTodosOsCredores() {
		todosOsCredores = credorDAO.listarTodos();
		return todosOsCredores;
	}

	public void setTodosOsCredores(List<Credor> todosOsCredores) {
		this.todosOsCredores = todosOsCredores;
	}

	public List<Compra> getListaDeComprasPorCredorNoMes() {
		return listaDeComprasPorCredorNoMes;
	}

	public void setListaDeComprasPorCredorNoMes(List<Compra> listaDeComprasPorCredorNoMes) {
		this.listaDeComprasPorCredorNoMes = listaDeComprasPorCredorNoMes;
	}

	public double getValorDaCobrancaMes() {
		return valorDaCobrancaMes;
	}

	public void setValorDaCobrancaMes(double valorDaCobrancaMes) {
		this.valorDaCobrancaMes = valorDaCobrancaMes;
	}
	

	public double getValorDaCobrancaEmAbertoNoMes() {
		return valorDaCobrancaEmAbertoNoMes;
	}

	public void setValorDaCobrancaEmAbertoNoMes(double valorDaCobrancaEmAbertoNoMes) {
		this.valorDaCobrancaEmAbertoNoMes = valorDaCobrancaEmAbertoNoMes;
	}

	public double getValorDaCobrancaPagoNoMes() {
		return valorDaCobrancaPagoNoMes;
	}

	public void setValorDaCobrancaPagoNoMes(double valorDaCobrancaPagoNoMes) {
		this.valorDaCobrancaPagoNoMes = valorDaCobrancaPagoNoMes;
	}

	@SuppressWarnings("deprecation")
	public void mes() {
		double valor = 0;
		double valorPago = 0;
		double valorEmAberto = 0;
		setCredor(credorDAO.buscarPorId(getCredorId()));
		List<Compra> comprasDoCredor = getCredor().getCompras();

		List<Compra> listaTemp = new ArrayList<Compra>();

		for (Compra compra : comprasDoCredor) {
			if (compra.getData().getMonth() == getMes()) {
				if(compra.getSituacao() != null) {
					valorPago += compra.getValor();
				} else {
					valorEmAberto += compra.getValor();
				}
				valor += compra.getValor();
				listaTemp.add(compra);
			}
		}
		setListaDeComprasPorCredorNoMes(listaTemp);
		setValorDaCobrancaPagoNoMes(valorPago);
		setValorDaCobrancaEmAbertoNoMes(valorEmAberto);
		setValorDaCobrancaMes(valor);
	}

}
