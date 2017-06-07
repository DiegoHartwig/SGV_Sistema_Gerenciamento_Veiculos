package br.net.hartwig.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import br.net.hartwig.dao.ComponenteDAO;
import br.net.hartwig.dao.ManutencaoDAO;
import br.net.hartwig.dao.VeiculoDAO;
import br.net.hartwig.model.Componente;
import br.net.hartwig.model.Manutencao;
import br.net.hartwig.model.Veiculo;


/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
@ManagedBean(name = "manutencaoBean")
@SessionScoped
public class ManutencaoBean implements Serializable {	

	private static final long serialVersionUID = -687895657506126197L;
	
	private Manutencao manutencao = new Manutencao();	
	private DataModel<Manutencao> manutencoes;
	private int veiculo_id;
	private int componente_id;
	//Variável utilizada no DataTable para filtrar manutenções
	private List<Manutencao> manutencoesFiltradas;
	//Variável que armazena o chamado selecionado no dataTable
	private Manutencao manutencaoSelecionada;	
	
	public Manutencao getManutencao() {
		return manutencao;
	}


	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}


	public int getVeiculo_id() {
		return veiculo_id;
	}


	public void setVeiculo_id(int veiculo_id) {
		this.veiculo_id = veiculo_id;
	}


	public int getComponente_id() {
		return componente_id;
	}


	public void setComponente_id(int componente_id) {
		this.componente_id = componente_id;
	}


	public List<Manutencao> getManutencoesFiltradas() {
		return manutencoesFiltradas;
	}


	public void setManutencoesFiltradas(List<Manutencao> manutencoesFiltradas) {
		this.manutencoesFiltradas = manutencoesFiltradas;
	}


	public Manutencao getManutencaoSelecionada() {
		return manutencaoSelecionada;
	}


	public void setManutencaoSelecionada(Manutencao manutencaoSelecionada) {
		this.manutencaoSelecionada = manutencaoSelecionada;
	}


	public void setManutencoes(DataModel<Manutencao> manutencoes) {
		this.manutencoes = manutencoes;
	}


	//Método que retorna uma lista no datamodel
	public DataModel<Manutencao> getManutencoes() {		
		ManutencaoDAO dao = new ManutencaoDAO();
		try {			
			List<Manutencao> lista = dao.GetALL();		
			manutencoes = new ListDataModel<Manutencao>(lista);
		} catch (Exception e) {

		}		
		return manutencoes;
	}
	
	
	public void novaManutencao() {
		manutencao = new Manutencao();	
		
	}

	// Método Add manutenção
	public String addManutencao() {
		String retorno = "erro";
		int numero = 0;
		try {			
			ManutencaoDAO dao = new ManutencaoDAO();
			
			VeiculoDAO veiculoDAO = new VeiculoDAO();
			
			ComponenteDAO componenteDAO = new ComponenteDAO();			

			manutencao.setVeiculo(veiculoDAO.Get(veiculo_id));

			manutencao.setComponente(componenteDAO.Get(componente_id));			
		
			dao.Salvar(manutencao);			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Manutenção número: "+numero+" realizada com sucesso"));
			retorno = "manutencoes";		
			

		} catch (Exception ex) {

		}
		return retorno;

	}

	// Carregar Lista de veículos
	public Collection<SelectItem> getCarregarVeiculos() {
		VeiculoDAO dao = new VeiculoDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione o veículo --"));
		List<Veiculo> listaVeiculo = dao.GetALL();

		for (int i = 0; i < listaVeiculo.size(); i++) {
			lista.add(new SelectItem(listaVeiculo.get(i).getId(), listaVeiculo.get(i).getPlaca()));
		}

		return lista;
	}
	
	// Carregar Lista de componentes
	public Collection<SelectItem> getCarregarComponentes() {
		ComponenteDAO dao = new ComponenteDAO();
		Collection<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("-- Selecione o componente --"));
		List<Componente> listaComponente = dao.GetALL();

		for (int i = 0; i < listaComponente.size(); i++) {
			lista.add(new SelectItem(listaComponente.get(i).getId(), listaComponente.get(i).getNome()));
		}

		return lista;
	}


	
	
	//Gerar PDF
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	    //cria o documento
	    Document pdf = (Document) document;        
	    //seta a margin e página, precisa estar antes da abertura do documento, ou seja da linha: pdf.open()
	    pdf.setMargins(5f, 5f, 5f, 5f);	    
	    pdf.setPageSize(PageSize.LETTER);
	    pdf.addTitle("Relatório de Chamados");
	    pdf.open();
	    //aqui pega o contexto para formar a url da imagem
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "LogoSGSD.png";
	    //cria a imagem e passando a url
	    Image image = Image.getInstance(logo);
	    //alinha ao centro
	    image.setAlignment(Image.ALIGN_CENTER);
	    //adiciona a img ao pdf
	    pdf.add(image);
	    //adiciona um paragrafo ao pdf, alinha também ao centro
	    Paragraph p = new Paragraph("Relatório de Chamados");
	    p.setAlignment("center");
	    pdf.add(p);
	}
	
	
	
	

}
