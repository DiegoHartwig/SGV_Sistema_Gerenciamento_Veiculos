
package br.net.hartwig.bean;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.ComponenteDAO;
import br.net.hartwig.model.Componente;

/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "componenteBean")

public class ComponenteBean implements Serializable{	

	private static final long serialVersionUID = 6681955626884519265L;

	private Componente componente = new Componente();
	
	private ComponenteDAO componenteDAO = new ComponenteDAO();

	private DataModel<Componente> componentes;
	
	//Variável que armazena o usuario selecionado no dataTable
	private Componente componenteSelecionado;		
	

	public ComponenteDAO getComponenteDAO() {
		return componenteDAO;
	}

	public void setComponenteDAO(ComponenteDAO componenteDAO) {
		this.componenteDAO = componenteDAO;
	}
	
	public Componente getComponente() {
		return componente;
	}

	public void setComponentes(DataModel<Componente> componentes) {
		this.componentes = componentes;
	}

	public Componente getComponenteSelecionado() {
		return componenteSelecionado;
	}

	public void setComponenteSelecionado(Componente componenteSelecionado) {
		this.componenteSelecionado = componenteSelecionado;
	}	

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public void selecionarComponente() {
		this.componente = componentes.getRowData();
	}

	public DataModel<Componente> getComponentes() {
		ComponenteDAO dao = new ComponenteDAO();

		try {
			List<Componente> lista = dao.GetALL();
			componentes = new ListDataModel<Componente>(lista);
		} catch (Exception e) {

		}

		return componentes;
	}

	// delete 
	public String deleteMarca() {
		this.componente = componentes.getRowData();

		String retorno = "erro";

		try {
			ComponenteDAO dao = new ComponenteDAO();
			
			dao.Delete(componente);
			
			String nome = componente.getNome();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Componente: "+nome+", excluído do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updatecomponente() {

		String retorno = "erro";

		try {
			ComponenteDAO dao = new ComponenteDAO();				
			
			dao.Update(componente);
			
			String nome = componente.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Componente "+nome+", atualizado com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novoComponente() {
		componente = new Componente();
	}

	public String addComponente() {

		String retorno = "erro";

		try {
			ComponenteDAO dao = new ComponenteDAO();

			String nome = componente.getNome();
			dao.Salvar(componente);			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Componente: "+nome+", cadastrado com sucesso"));

			retorno = "componente";
		} catch (Exception ex) {

		}
		return retorno;

	}	

}
