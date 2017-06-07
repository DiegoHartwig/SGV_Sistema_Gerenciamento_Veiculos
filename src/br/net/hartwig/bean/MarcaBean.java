
package br.net.hartwig.bean;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.net.hartwig.dao.MarcaDAO;
import br.net.hartwig.model.Marca;
/**  
 * SGMV 2017
 * Author: Diego Michel Hartwig
 */
@SessionScoped
@ManagedBean(name = "marcaBean")

public class MarcaBean implements Serializable{	

	private static final long serialVersionUID = 6369743954849018459L;

	private Marca marca = new Marca();
	
	private MarcaDAO marcaDAO = new MarcaDAO();

	private DataModel<Marca> marcas;
	
	//Variável que armazena o usuario selecionado no dataTable
	private Marca marcaSelecionado;	
	

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public MarcaDAO getMarcaDAO() {
		return marcaDAO;
	}

	public void setMarcaDAO(MarcaDAO marcaDAO) {
		this.marcaDAO = marcaDAO;
	}

	public Marca getMarcaSelecionado() {
		return marcaSelecionado;
	}

	public void setMarcaSelecionado(Marca marcaSelecionado) {
		this.marcaSelecionado = marcaSelecionado;
	}

	public void setMarcas(DataModel<Marca> marcas) {
		this.marcas = marcas;
	}

	public void selecionarMarca() {
		this.marca = marcas.getRowData();
	}

	public DataModel<Marca> getMarcas() {
		MarcaDAO dao = new MarcaDAO();

		try {
			List<Marca> lista = dao.GetALL();
			marcas = new ListDataModel<Marca>(lista);
		} catch (Exception e) {

		}

		return marcas;
	}

	// delete 
	public String deleteMarca() {
		this.marca = marcas.getRowData();

		String retorno = "erro";

		try {
			MarcaDAO dao = new MarcaDAO();
			
			dao.Delete(marca);
			
			String nome = marca.getNome();

			// Mensagem de confirmação após deletar
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca: "+nome+", excluída do Sistema"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public String updateMarca() {

		String retorno = "erro";

		try {
			MarcaDAO dao = new MarcaDAO();				
			
			dao.Update(marca);
			
			String nome = marca.getNome();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca "+nome+",atualizada com sucesso"));

			retorno = "listar";
		} catch (Exception ex) {

		}
		return retorno;

	}

	public void novaMarca() {
		marca = new Marca();
	}

	public String addMarca() {

		String retorno = "erro";

		try {
			MarcaDAO dao = new MarcaDAO();

			String nome = marca.getNome();
			dao.Salvar(marca);			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marca: "+nome+", cadastrada com sucesso"));

			retorno = "marca";
		} catch (Exception ex) {

		}
		return retorno;

	}	

}
