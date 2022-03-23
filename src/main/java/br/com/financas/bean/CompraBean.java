package br.com.financas.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Compra compra;

	private Credor credor = new Credor();

	private Responsavel responsavel = new Responsavel();

	private Integer credorId, responsavelId, compraId;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private CompraDAO compraDAO;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private CredorDAO credorDAO;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ResponsavelDAO responsavelDAO;

	public CompraBean() {
		super();
		compra = new Compra();
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

	public Integer getCompraId() {
		return compraId;
	}

	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}

	@Transactional
	public void salvar() {
		Credor buscarCredorPorId = credorDAO.buscarPorId(getCredorId());
		setCredor(buscarCredorPorId);
		getCompra().setCredor(getCredor());

		Responsavel buscarResponsavelPorId = responsavelDAO.buscarPorId(getResponsavelId());
		setResponsavel(buscarResponsavelPorId);
		getCompra().setResponsavel(getResponsavel());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCompra().getData());

		@SuppressWarnings("deprecation")
		int mesDaCompra = getCompra().getData().getMonth();

		List<Compra> listaComprasTemp = new ArrayList<Compra>();

		if (getCompra().getId() == null) {
			if (getCompra().getParcelas() == 0) {
				getCompra().setSituacao("PAGO");
				listaComprasTemp.add(getCompra());
			} else {
				double valor = getCompra().getValor() / getCompra().getParcelas();
				for (int i = 1; i <= getCompra().getParcelas(); i++) {

					Compra compraTemp = new Compra(getCompra().getDescricao(), getCompra().getSituacao(), valor,
							calendar.getTime(), i, buscarCredorPorId, buscarResponsavelPorId);

					listaComprasTemp.add(compraTemp);

					calendar.set(Calendar.MONTH, mesDaCompra + i);
					calendar.setTime(calendar.getTime());

				}
			}

			for (Compra compra : listaComprasTemp) {
				if (compra.getId() == null) {
					System.out.println("Salvando ID: " + compra.getId());
					compraDAO.salvar(compra);
				} else {
					System.out.println("Editando ID: " + compra.getId());
					compraDAO.editar(compra);
				}
			}
		} else {
			compraDAO.editar(getCompra());
		}

		setCredor(new Credor());
		setResponsavel(new Responsavel());
		setCompra(new Compra());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("COMPRA CADASTRADA!"));

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

	public String editarCompra(Compra compra) {
		setCompra(compra);
		System.out.println(getCompra().getId());
		return "cadastrocompra?faces-redirect=true";

	}

	@Transactional
	public void excluir(Integer id) {
		Compra buscarPorId = compraDAO.buscarPorId(id);
		compraDAO.remover(buscarPorId);
	}

}
