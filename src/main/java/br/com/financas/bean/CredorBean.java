package br.com.financas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	private double valorTotalEmCompraPorCredorEmAberto;
	private double valorTotalEmCompraPorCredorPago;
	private double valorTotalEmCompraPorCredor;

	@SuppressWarnings("cdi-ambiguous-dependency")
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

	public double getValorTotalEmCompraPorCredorEmAberto() {
		return valorTotalEmCompraPorCredorEmAberto;
	}

	public void setValorTotalEmCompraPorCredorEmAberto(double valorTotalEmCompraPorCredorEmAberto) {
		this.valorTotalEmCompraPorCredorEmAberto = valorTotalEmCompraPorCredorEmAberto;
	}

	public double getValorTotalEmCompraPorCredorPago() {
		return valorTotalEmCompraPorCredorPago;
	}

	public void setValorTotalEmCompraPorCredorPago(double valorTotalEmCompraPorCredorPago) {
		this.valorTotalEmCompraPorCredorPago = valorTotalEmCompraPorCredorPago;
	}

	public double getValorTotalEmCompraPorCredor() {
		return valorTotalEmCompraPorCredor;
	}

	public void setValorTotalEmCompraPorCredor(double valorTotalEmCompraPorCredor) {
		this.valorTotalEmCompraPorCredor = valorTotalEmCompraPorCredor;
	}

	public void buscarCompras() {
		double total = 0;
		double pago = 0;
		double aberto = 0;
		Credor buscarCredorPorId = credorDAO.buscarPorId(getCredorId());
		List<Compra> listaDeCompras = buscarCredorPorId.getCompras();

		for (Compra compra : listaDeCompras) {
			if (compra.getSituacao() == null) {
				aberto += compra.getValor();
			} else {
				pago += compra.getValor();
			}
			total += compra.getValor();

		}
		setCompras(listaDeCompras);
		
		setValorTotalEmCompraPorCredor(total);
		setValorTotalEmCompraPorCredorEmAberto(aberto);
		setValorTotalEmCompraPorCredorPago(pago);

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
	public String editarCredor(Integer id) {
		Credor buscarPorId = credorDAO.buscarPorId(id);
		setCredor(buscarPorId);
		return "cadastrocredor?faces-redirect=true";
	}

	@Transactional
	public void excluirCredor(Integer id) {
		Credor buscarPorId = credorDAO.buscarPorId(id);

		if (buscarPorId.getCompras().isEmpty()) {
			credorDAO.excluir(buscarPorId);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					buscarPorId.getNome() + " NÃO PODE SER EXCLUIDO(A)!", "POSSUI COMPRA EM SEU NOME!"));
		}

	}

}