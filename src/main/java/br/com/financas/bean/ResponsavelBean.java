package br.com.financas.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.financas.dao.ResponsavelDAO;
import br.com.financas.model.Compra;
import br.com.financas.model.Responsavel;

@Named(value = "responsavelBean")
@Stateless
public class ResponsavelBean {

	private Responsavel responsavel = new Responsavel();

	private List<Compra> comprasPorResponsavel = new ArrayList<Compra>();

	private double valorTotalEmComprasPeloResponsavel;

	private double valorTotalEmCompraPorResponsavelEmAberto;
	private double valorTotalEmCompraPorResponsavelPago;
	private double valorTotalEmCompraPorResponsavel;

	private Integer responsavelId;

	@SuppressWarnings("cdi-ambiguous-dependency")
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

	public double getValorTotalEmCompraPorResponsavelEmAberto() {
		return valorTotalEmCompraPorResponsavelEmAberto;
	}

	public void setValorTotalEmCompraPorResponsavelEmAberto(double valorTotalEmCompraPorResponsavelEmAberto) {
		this.valorTotalEmCompraPorResponsavelEmAberto = valorTotalEmCompraPorResponsavelEmAberto;
	}

	public double getValorTotalEmCompraPorResponsavelPago() {
		return valorTotalEmCompraPorResponsavelPago;
	}

	public void setValorTotalEmCompraPorResponsavelPago(double valorTotalEmCompraPorResponsavelPago) {
		this.valorTotalEmCompraPorResponsavelPago = valorTotalEmCompraPorResponsavelPago;
	}

	public double getValorTotalEmCompraPorResponsavel() {
		return valorTotalEmCompraPorResponsavel;
	}

	public void setValorTotalEmCompraPorResponsavel(double valorTotalEmCompraPorResponsavel) {
		this.valorTotalEmCompraPorResponsavel = valorTotalEmCompraPorResponsavel;
	}

	public void buscarCompraPorResponsavel() {
		double total = 0;
		double pago = 0;
		double aberto = 0;

		Responsavel buscarPorId = responsavelDAO.buscarPorId(getResponsavelId());
		List<Compra> listaDeCompras = buscarPorId.getCompras();

		for (Compra compra : listaDeCompras) {
			if (compra.getSituacao() == null) {
				aberto += compra.getValor();
			} else {
				pago += compra.getValor();
			}
			total += compra.getValor();

		}
		setComprasPorResponsavel(listaDeCompras);

		setValorTotalEmCompraPorResponsavel(total);
		setValorTotalEmCompraPorResponsavelEmAberto(aberto);
		setValorTotalEmCompraPorResponsavelPago(pago);

	}

	public String cadastrarResponsavel() {
		responsavelDAO.salvar(getResponsavel());
		setResponsavel(new Responsavel());
		return "cadastrocompra?faces-redirect=true";
	}

	public String editarResponsavel(Integer id) {
		Responsavel buscarPorId = responsavelDAO.buscarPorId(id);
		setResponsavel(buscarPorId);
		return "cadastroresponsavel?faces-redirect=true";
	}

	public void excluirResponsavel(Integer id) {
		Responsavel buscarPorId = responsavelDAO.buscarPorId(id);
		if (buscarPorId.getCompras().isEmpty()) {
			responsavelDAO.remover(buscarPorId);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					buscarPorId.getNome() + " NÃO PODE SER EXCLUIDO(A)!", "POSSUI COMPRA EM SEU NOME!"));
		}

	}

}
