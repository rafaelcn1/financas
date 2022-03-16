package br.com.financas.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.financas.dao.CompraDAO;
import br.com.financas.dao.CredorDAO;
import br.com.financas.dao.ResponsavelDAO;
import br.com.financas.model.Compra;
import br.com.financas.model.Credor;
import br.com.financas.model.Responsavel;

@Named(value = "compraBean")
@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompraBean {

	private Compra compra = new Compra();

	private Credor credor = new Credor();

	private Responsavel responsavel = new Responsavel();

	private Integer credorId, responsavelId;

	@Inject
	private CompraDAO compraDAO;

	@Inject
	private CredorDAO credorDAO;

	@Inject
	private ResponsavelDAO responsavelDAO;

	public CompraBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Credor getCredor() {
		return credor;
	}

	public void setCredor(Credor credor) {
		this.credor = credor;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Integer getCredorId() {
		return credorId;
	}

	public void setCredorId(Integer credorId) {
		this.credorId = credorId;
	}

	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}

	@Transactional
	public void salvar() {
		Credor buscarCredorPorId = credorDAO.buscarPorId(getCredorId());
		setCredor(buscarCredorPorId);
		getCompra().setCredor(getCredor());

		Responsavel buscarResponsavelPorId = responsavelDAO.buscarPorId(getResponsavelId());
		setResponsavel(buscarResponsavelPorId);
		getCompra().setResponsavel(getResponsavel());

		compraDAO.salvar(getCompra());

		setCredor(new Credor());
		setCompra(new Compra());
		setResponsavel(new Responsavel());
	}

	public String cadastrarCredor() {
		return "cadastrocredor?faces-redirect=true";
	}

	public String cadastroResponsavel() {
		return "cadastroresponsavel?faces-redirect=true";
	}

	public List<Compra> getListarCompras() {
		return compraDAO.listarTodas();
	}

}
